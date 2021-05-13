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
                caller.gridView01.setData(res);
                caller.gridView02.setData(res);
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
            var fileIds = [];
            var files = ax5.util.deepCopy(caller.formView01.UPLOAD.uploadedFiles);
            $.each(files, function (idx, o) {
                fileIds.push(o.id);
            });
            item.fileIdList = fileIds;
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
            excel: function () {},
            fn1: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_DELETE);
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
        this.target.attr('onsubmit', 'return false;');
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });

        this.useYn = $('.js-useYn').on('change', function () {
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
        this.filter = $('.js-filter');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 50,
            useYn: this.useYn.val(),
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

        if (item.bizno && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.bizno)) {
            axDialog.alert('전화번호 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }

        return true;
    },
    initUploader: function () {
        var _this = this;
        _this.UPLOAD = new ax5.ui.uploader({
            //debug: true,
            target: $('[data-ax5uploader="upload1"]'),
            form: {
                action: '/api/v1/files/upload',
                fileName: 'file',
            },
            multiple: true,
            manualUpload: false,
            progressBox: true,
            progressBoxDirection: 'left',
            dropZone: {
                target: $('[data-uploaded-box="upload1"]'),
            },
            uploadedBox: {
                target: $('[data-uploaded-box="upload1"]'),
                icon: {
                    download: '<i class="cqc-download" aria-hidden="true"></i>',
                    delete: '<i class="cqc-minus" aria-hidden="true"></i>',
                },
                columnKeys: {
                    name: 'fileNm',
                    type: 'extension',
                    size: 'fileSize',
                    uploadedName: 'saveName',
                    uploadedPath: '',
                    downloadPath: '',
                    previewPath: '',
                    thumbnail: '',
                },
                lang: {
                    supportedHTML5_emptyListMsg: '<div class="text-center" style="padding-top: 30px;">첨부파일이 없습니다. </div>',
                    emptyListMsg: '<div class="text-center" style="padding-top: 30px;">Empty of List.</div>',
                },
                onchange: function () {},
                onclick: function () {
                    var fileIndex = this.fileIndex;
                    var file = this.uploadedFiles[fileIndex];

                    switch (this.cellType) {
                        case 'delete':
                            axDialog.confirm(
                                {
                                    theme: 'danger',
                                    msg: '삭제하시겠습니까?',
                                },
                                function () {
                                    if (this.key == 'ok') {
                                        $.ajax({
                                            contentType: 'application/json',
                                            method: 'get',
                                            url: '/api/v1/files/delete',
                                            /*
                                        data: JSON.stringify([{
                                            id: file.id
                                        }]),
                                        */
                                            data: { id: file.id },
                                            success: function (res) {
                                                if (res.error) {
                                                    alert(res.error.message);
                                                    return;
                                                }
                                                _this.UPLOAD.removeFile(fileIndex);
                                            },
                                        });
                                    }
                                }
                            );
                            break;

                        case 'download':
                            if (file.download) {
                                location.href = file.download;
                            }
                            break;
                    }
                },
            },
            validateSelectedFiles: function () {
                var limitCount = 5;
                if (this.uploadedFiles.length + this.selectedFiles.length > limitCount) {
                    alert('You can not upload more than ' + limitCount + ' files.');
                    return false;
                }
                return true;
            },
            onprogress: function () {},
            onuploaderror: function () {
                console.log(this.error);
                axDialog.alert(this.error.message);
            },
            onuploaded: function () {},
            onuploadComplete: function () {},
        });
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

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        this.initUploader();
        console.log(this.model);
        this.initEvent();
    },
});
fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            onPageChange: function (pageNumber) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { pageNumber: pageNumber });
            },
            showRowSelector: false,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-02"]'),
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
