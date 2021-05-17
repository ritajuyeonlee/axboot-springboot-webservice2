var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/chk',
            data: caller.formView.getData(),
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
            type: 'POST',
            url: '/api/v1/chk',
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
    MODAL_OPEN: function (caller, act, data) {
        if (!data) data = {};

        axboot.modal.open({
            width: 780,
            height: 520,
            iframe: {
                param: 'guestNm=' + (data.guestNm || '') + '&guestTel=' + (data.guestTel || '') + '&email=' + (data.email || ''),
                url: 'reservation-content.jsp',
            },
            header: { title: '투숙객 조회' },
            callback: function (data) {
                caller.formView01.setGuestValue(data);
                this.close();
            },
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

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.formView01.initView();
    this.gridView01.initView();

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
            fn1: function () {},
        });
        $('[data-page-btn="fn1"]').text('신규등록');
    },
});

fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'rsvNum', label: '예약 번호', width: 100, align: 'center', editor: 'text' },
                {
                    key: 'rsvDt',
                    label: '예약일',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'date',
                        config: {
                            content: {
                                config: {
                                    mode: 'year',
                                    selectMode: 'day',
                                },
                            },
                        },
                    },
                },
                { key: 'guestNm', label: '투숙객 명', width: 100, align: 'center', editor: 'text' },
                {
                    key: 'roomTypCd',
                    label: '객실타입',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_ROOM_TYPE'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_ROOM_TYPE'].map[this.value];
                    },
                },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center', editor: 'text' },
                {
                    key: 'arrDt',
                    label: '도착 일자',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'date',
                        config: {
                            content: {
                                config: {
                                    mode: 'year',
                                    selectMode: 'day',
                                },
                            },
                        },
                    },
                },
                {
                    key: 'depDt',
                    label: '출발 일자',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'date',
                        config: {
                            content: {
                                config: {
                                    mode: 'year',
                                    selectMode: 'day',
                                },
                            },
                        },
                    },
                },
                {
                    key: 'saleTypCd',
                    label: '판매유형',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_SALE_TYPE'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_SALE_TYPE'].map[this.value];
                    },
                },
                {
                    key: 'sttusCd',
                    label: '투숙상태',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_STAY_STATUS'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_STAY_STATUS'].map[this.value];
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

//== view 시작
/**
 * searchView
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
    initView: function () {
        var _this = this;

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        axboot.buttonClick(this, 'data-form-view-01-btn', {
            guestsearch: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN, _this.getModalParams());
            },
        });
    },
    getModalParams: function () {
        return {
            guestNm: this.model.get('guestNm'),
            guestTel: this.model.get('guestTel'),
            email: this.model.get('email'),
        };
    },
    setGuestValue: function (data) {
        if (!data) return;
        console.log(data);
        this.model.set('guestNm', data.guestNm);
        this.model.set('guestTel', data.guestTel);
        this.model.set('email', data.email);
        this.model.set('guestNmEng;', data.guestNmEng);
        this.model.set('brth;', data.brth);
        this.model.set('gender;', data.gender);
        this.model.set('langCd;;', data.langCd);
    },
});
