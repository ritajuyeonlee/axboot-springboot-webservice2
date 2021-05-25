var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close(data);
        }
    },
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/rsv/' + modalParams.id,
            callback: function (res) {
                caller.formView01.setData(res);
                caller.gridView01.setData(res.chkMemoList);
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            item.chkMemoList = [].concat(caller.gridView01.getData());

            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/rsv',
                data: JSON.stringify(item),
                callback: function (res) {
                    axToast.push('저장 되었습니다');
                },
            });
        }
    },
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
                console.log(data);
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

var CODE = {};

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
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },

            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
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
        return $.extend({}, data, { sttusCd: 'RSV_01' });
    },
    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
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

        _this.target = $('.js-form');

        this.nightCnt = _this.target.find('[data-ax-path="nightCnt"]');
        _this.target.find('[data-ax5picker="depDt"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });
        _this.target.find('[data-ax5picker="arrDt"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });
        _this.target.find('[data-ax5picker="brth"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });

        this.arrDt = _this.target.find('[data-ax-path="arrDt"]').on('change', function () {
            var arrDt = $(this).val();
            var depDt = _this.depDt.val();
            if (!arrDt || !depDt) return;
            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('depDt', momArrDt.add(nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.depDt = _this.target.find('[data-ax-path="depDt"]').on('change', function () {
            var arrDt = _this.arrDt.val();
            var depDt = $(this).val();
            if (!arrDt || !depDt) return;
            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('arrDt', momDepDt.add(-nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.nightCnt.on('change', function () {
            var arrDt = _this.arrDt.val();
            if (!arrDt) return;
            var nightCnt = _this.nightCnt.val();
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('nightCnt', nightCnt);
            }
            _this.model.set('depDt', moment(arrDt).add(nightCnt, 'days').format('yyyy-MM-DD'));
        });

        axboot.buttonClick(this, 'data-form-view-01-btn', {
            guestsearch: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN, _this.getModalParams());
            },
        });

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
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
        this.model.set('guestId', data.id);
        this.model.set('guestNm', data.guestNm);
        this.model.set('guestTel', data.guestTel);
        this.model.set('email', data.email);
        this.model.set('guestNmEng', data.guestNmEng);
        this.model.set('brth', data.brth);
        this.model.set('gender', data.gender);
        this.model.set('langCd', data.langCd);
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
                {
                    key: 'memoDtti',
                    label: '메모 일시',
                    width: 150,
                    align: 'center',
                    formatter: function () {
                        var date = this.value;
                        if (!date && this.item.createdAt) createdAt = this.item.createdAt;
                        if (!date) return '';

                        return moment(date).format('yyyy-MM-DD');
                    },
                },
                { key: 'memoCn', label: '메모 내용', width: '*', align: 'left', editor: { type: 'textarea' } },
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
        var memoDtti = moment().format('yyyy-MM-DD hh:mm');
        this.target.addRow({ __created__: true, memoDtti: memoDtti }, 'last'); //delYn:'Y'
    },
});
