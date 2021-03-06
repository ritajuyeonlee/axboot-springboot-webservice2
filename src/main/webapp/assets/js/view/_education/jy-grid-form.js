var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/education/jyGridForm',
            data: caller.searchView.getData(),
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            var fileIds = [];
            var files = ax5.util.deepCopy(caller.formView01.UPLOAD.uploadedFiles);
            $.each(files, function (idx, o) {
                fileIds.push(o.id);
            });
            item.fileIdList = fileIds;
            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/education/jyGridForm',
                data: JSON.stringify(item),
                callback: function (res) {
                    axToast.push('저장 되었습니다');
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                },
            });
        }
    },
    ITEM_CLICK: function (caller, act, data) {
        //그리드에값을 바인딩 할수있으나 id를 통해서 조회후 바인딩하도록 프로세스 정의
        //fnObj.formView01.setData(this.item);
        var id = (data || {}).id;
        if (!id) {
            axDialog.alert('id는 필수입니다.');
            return false;
        }
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/education/jyGridForm/' + id,
            callback: function (res) {
                caller.formView01.setData(res);
            },
        });
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                $('[data-ax-path="companyNm"]').focus();
            }
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
    EXCEL_DOWN: function (caller, act, data) {
        let frm = document['excelForm'];
        frm.action = '/api/v1/education/jyGridForm/excelDown';
        frm.submit();
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {
                ACTIONS.dispatch(ACTIONS.EXCEL_DOWN);
            },
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
        this.filter = $('#filter');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
        };
    },
});

/**
 * gridView
 */

fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            onPageChange: function (pageNumber) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { pageNumber: pageNumber });
            },
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),

            columns: [
                { key: 'id', label: COL('company.id'), width: 50, align: 'left', editor: 'text' },
                { key: 'companyNm', label: COL('company.name'), width: 200, align: 'center', editor: 'text' },
                { key: 'ceo', label: COL('company.ceo'), width: 100, align: 'center', editor: 'text' },
                {
                    key: 'useYn',
                    label: COL('use.or.not'),
                    align: 'center',
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    fnObj.formView01.setData(this.item);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                //                delete this.deleted;
                //                return this.key;
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});

/**
 * formView
 */

fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return { useYn: 'Y' };
    },
    getData: function () {
        var item = {};
        this.target.find('input,select').each(function (i, elem) {
            //var $elem = $(elem);
            var $elem = $(this);
            var name = $elem.data('axPath');
            var value = $elem.val() || '';
            item[name] = value;
        });
        return item;
    },
    setData: function (item) {
        var value;
        for (var prop in item) {
            value = item[prop] || '';
            $('[data-ax-path="' + prop + '"]').val(value);
        }
    },
    initView: function () {
        var _this = this;
        //fnObj.formView01// 다른프레임넘어오는 코드오면 다시 window가르키게되는거 방지
        // setTimeout(function () {
        //     this //window
        // })

        _this.target = $('.js-form'); //폼 타겟팅
        // _this.model = new ax5.ui.binder(); //바인더객체 주입
        // _this.model.setModel({}, _this.target);

        // console.log(_this.model.get());
    },
});
