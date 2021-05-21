var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close(data);
        }
    },
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: modalParams,
            callback: function (res) {
                caller.formView01.clear();
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
    PAGE_CHOICE: function (caller, act, data) {
        var list = caller.gridView01.getData('selected');
        if (list.length > 0) {
            if (parent && parent.axboot && parent.axboot.modal) {
                parent.axboot.modal.callback(list[0]);
            }
        } else {
            alert(LANG('ax.script.requireselect'));
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
            },
        });
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
});

var CODE = {};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    var _this = this;

    _this.pageButtonView.initView();
    _this.gridView01.initView();
    _this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CHOICE);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
        });
    },
});
/**
 * gridView
 */

fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            frozenColumnIndex: 0,
            multipleSelect: false,
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
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
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

        if (item.bizno && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.bizno)) {
            axDialog.alert('연락처 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }

        return true;
    },

    initView: function () {
        var _this = this; // fnObj.formView01
        _this.target = $('.js-form');
        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
        this.initEvent();
    },
});
