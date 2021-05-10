var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data, { pageSize: 3 });
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/education/jyGrid/pages',
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
        var saveList = [].concat(caller.gridView01.getData('modified'));
        saveList = saveList.concat(caller.gridView01.getData('deleted'));

        axboot.ajax({
            type: 'PUT',
            url: ['samples', 'parent'],
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
fnObj.selectItems = [
    { value: 'Y', text: '사용' },
    { value: 'N', text: '미사용' },
];
/**
 * gridView
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
                { key: 'key', label: 'KEY', width: 50, align: 'center', editor: 'text' },
                {
                    key: undefined,
                    label: 'VALUE',
                    width: 350,
                    align: 'left',
                    columns: [
                        {
                            key: 'value',
                            label: 'display',
                            width: 100,
                            align: 'left',
                        },
                        {
                            key: 'value',
                            label: 'textarea',
                            width: 100,
                            align: 'left',
                            editor: {
                                type: 'textarea',
                            },
                        },
                    ],
                },
                {
                    key: 'etc1',
                    label: 'money',
                    width: 100,
                    align: 'center',
                    editor: 'text',
                    formatter: 'money',
                },

                {
                    key: 'etc2',
                    label: 'number',
                    width: 100,
                    align: 'center',
                    editor: 'text',
                },
                {
                    key: 'etc3',
                    label: 'date',
                    width: 100,
                    align: 'center',
                    editor: 'text',
                },
                {
                    key: 'etc4',
                    label: 'select',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        var i = 0,
                            len = fnObj.selectItems.length,
                            value;
                        for (; i < len; i++) {
                            if (this.item.etc4 === (value = fnObj.selectItems[i].value)) {
                                break;
                            }
                        }
                        return value === 'Y' ? '사용' : '미사용';
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'value',
                                optionText: 'text',
                            },
                            options: fnObj.selectItems,
                        },
                    },
                },
                {
                    key: 'etc4',
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
                mergeCells: ['value', 'etc1', 'etc2'],
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
                delete this.deleted;
                return this.key;
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
