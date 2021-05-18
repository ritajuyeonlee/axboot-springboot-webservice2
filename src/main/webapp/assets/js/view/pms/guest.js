var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);

        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: paramObj,
            callback: function (res) {
                caller.formView01.clear();
                caller.gridView02.clear();
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();

            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/guest',
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
            url: '/api/v1/guest/' + id,
            callback: function (res) {
                caller.formView01.setData(res);
                caller.gridView02.clear();
                caller.gridView02.setData(res.chkList);
            },
        });
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                $('[data-ax-path="guestNm;"]').focus();
            }
        });
    },
    EXCEL_DOWN: function (caller, act, data) {
        var frm = $('.js-form').get(0);
        frm.action = '/api/v1/guest/exceldown';
        frm.enctype = 'application/x-www-form-urlencoded';
        frm.submit();
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
    this.gridView02.initView();
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
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });
        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');
    },
    getData: function () {
        return {
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val(),
        };
    },
});

/**
 * gridViewv
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'guestNm', label: '이름', width: 100, align: 'left', editor: 'readonly' },
                { key: 'gender', label: '성별', width: 100, align: 'left', editor: 'readonly' },
                { key: 'guestTel', label: '연락처', width: 100, align: 'center', editor: 'readonly' },
                { key: 'email', label: '이메일', width: 100, align: 'center', editor: 'readonly' },
                { key: 'brth', label: '생년월일', width: 100, align: 'center', editor: 'readonly' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                },
            },
        });
    },
});
/**
 * formView
 */

fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경

        if (typeof data.fileList != 'undefined' && data.fileList.length > 0) {
            _this.UPLOAD.setUploadedFiles(data.fileList);
        }
    },
    initEvent: function () {
        axboot.buttonClick(this, 'data-form-view-01-btn', {
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.FORM_CLEAR);
            },
        });
    },
    validate: function () {
        var item = this.model.get();

        console.log(this, this.model, item);
        var rs = this.model.validate();
        if (rs.error) {
            axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
                rs.error[0].jquery.focus();
            });
            return false;
        }

        // required 이외 벨리데이션 정의
        var pattern;
        if (item.email) {
            pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
            if (!pattern.test(item.email)) {
                axDialog.alert('이메일 형식을 확인하세요.', function () {
                    $('[data-ax-path="email"]').focus();
                });
                return false;
            }
        }

        if (item.guestTel && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.guestTel)) {
            axDialog.alert('전화번호 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }

        return true;
    },

    initView: function () {
        var _this = this;

        _this.target = $('.js-form'); //폼 타겟팅

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        this.initEvent();
    },
});

fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-02"]'),
            columns: [
                {
                    key: 'rsvDt',
                    label: '투숙일',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        return moment(this.item.arrDt).format('YY.M.D') + '-' + moment(this.item.depDt).format('YY.M.D');
                    },
                },
                { key: 'nightCnt', label: '숙박수', width: 50, align: 'center' },
                { key: 'roomNum', label: '객실번호', width: 80, align: 'center' },
                {
                    key: 'roomTypCd',
                    label: '객실타입',
                    width: 120,
                    align: 'center',
                    // formatter: function formatter() {
                    //     return parent.COMMON_CODE['PMS_ROOM_TYPE'].map[this.value];
                    // },
                },
                { key: 'rsvNum', label: '투숙번호', width: 120, align: 'center' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });
    },
});
