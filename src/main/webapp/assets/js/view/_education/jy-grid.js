var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data, { pageSize: 15 });

        //fnObj.type = 'myBatis';
        // paramObj.type = fnObj.type || '';

        axboot.ajax({
            type: 'GET',
            url: '/api/v1/education/jyGrid/pages',
            data: paramObj,
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
        var saveList = [].concat(caller.gridView01.getData());
        saveList = saveList.concat(caller.gridView01.getData('deleted'));

        axboot.ajax({
            type: 'PUT',
            url: '/api/v1/education/jyGrid',
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
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
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            searchPage: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {},
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
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });
        this.isPage = $('.js-isPage');

        this.companyNm = $('#companyNm');
        this.ceo = $('#ceo');
        this.bizno = $('#bizno');
        this.useYn = $('.js-useYn');
        //this.filter = $("#filter");
    },
    getData: function () {
        return {
            pageType: this.pageType,
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 0,

            companyNm: this.companyNm.val(),
            ceo: this.ceo.val(),
            bizno: this.bizno.val(),
            useYn: this.useYn.val(),
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
                { key: 'bizno', label: COL('company.bizno'), width: 100, align: 'center', editor: 'text' },
                { key: 'tel', label: COL('company.tel'), width: 100, align: 'center', editor: 'text' },
                { key: 'email', label: COL('company.email'), width: 100, align: 'center', editor: 'text' },
                {
                    key: 'useYn',
                    label: COL('use.or.not'),
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['USE_YN'].map[this.value];

                        /*
                        var i = 0,
                            len = fnObj.selectItems.length,
                            value;
                        for (; i < len; i++) {
                            if (this.item.useYn === (value = fnObj.selectItems[i].value)) {
                                break;
                            }
                        }
                        if (value === 'Y') {
                            return '사용';
                        } else if (value === 'N') {
                            return '미사용';
                        } else {
                            return '';
                        }
                        */
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['USE_YN'],
                        },
                    },
                },
                {
                    key: 'useYn',
                    label: 'checkbox',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'checkbox',
                        config: { trueValue: 'Y', falseValue: 'N' },
                    },
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
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
