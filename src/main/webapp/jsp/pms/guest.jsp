<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="css">
        <link rel="stylesheet" type="text/css"
            href="https://cdn.rawgit.com/ax5ui/ax5ui-uploader/master/dist/ax5uploader.css" />
    </jsp:attribute>
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <ax:script-lang key="ax.base" var="COL" />
        <script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-uploader/master/dist/ax5uploader.js"></script>
        <script type="text/javascript" src="<c:url value='/assets/plugins-fix/ckeditor/ckeditor.js' />"></script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/guest.js' />"></script>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script type="text/javascript">
        var UUID = "${UUID}";
        </script>
    </jsp:attribute>
    <jsp:attribute name="css">

        <style type="text/css">
        /*.editor1{width:100%; border:1px solid #D7D7D7; border-radius: 3px; overflow: hidden; background: white;}*/
        </style>
    </jsp:attribute>


    <jsp:body>
        <ax:page-buttons></ax:page-buttons>

    <div role="page-header">
        <form name="searchView0">
            <div data-ax-tbl class="ax-search-tbl">
                <div data-ax-tr>
                    <div data-ax-td style="width:300px">
                        <div data-ax-td-label>이름</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="filter" class="js-filter form-control"  />
                        </div>
                    </div>
                    <div data-ax-td style="width:300px">
                        <div data-ax-td-label>전화번호</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="filter" class="js-filter form-control"/>
                        </div>
                    </div>
                    <div data-ax-td style="width:500px">
                        <div data-ax-td-label>이메일</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="filter" class="js-filter form-control" />
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="H10"></div>
    </div>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            투숙객 목록 </h2>
                    </div>
                    
                </div> <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
                <form name="excelForm" method="post">
                </form>
    
    
            </div>
    
            <div class="col-md-7">
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            투숙객 정보 </h2>
                    </div>
                </div>
               
                <form name="form" class="js-form">
                    
                    <div data-ax-tbl="" id="" class="ax-form-tbl" style="">
                        <div data-ax-tr="" id="" class="" style="">
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">ID</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="id" class="form-control" value="" readonly="readonly">
    
                                </div>
                            </div>
                            <div data-ax-td=""  style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">이름</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="guestNm" class="form-control" value="">
    
                                </div>
                            </div>
    
                        </div>
                        <div data-ax-tr="" id="" class="" style="">
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">연락처</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="guestTel" class="form-control" value="">
    
                                </div>
                            </div>
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">이메일</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="email" class="form-control" value="">
    
                                </div>
                            </div>
    
                        </div>
                        <div data-ax-tr="" id="" class="" style="">
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">성별</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="gender" class="form-control" value="">
    
                                </div>
                            </div>
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">생일</div>
                                <div data-ax-td-wrap="">
    
                                    <input type="text" data-ax-path="gender" class="form-control" value="">
    
                                </div>
                            </div>
    
                        </div>
                       
                        <div data-ax-tr="" id="" class="" style="">
                            
                            <div data-ax-td="" id="" class="" style="width:50%;">
                                <div data-ax-td-label="" class="" style="width:150px;">비고</div>
                                <div data-ax-td-wrap="">
    
                                <textarea name="rmk" data-ax-path="rmk" rows="5" class="form-control"></textarea>
    
                                </div>
                            </div>
                        </div>
                        <ax:tr labelWidth="120px">
                            <ax:td label="첨부파일 <i class='icon-info-circled cp'></i>" width="100%">
                                <div data-ax5uploader="upload1">
                                    <input type="hidden" id="targetType" name="targetType" value="EDUCATION_TEACH" />
                                    <button data-ax5uploader-button="selector" class="btn btn-primary">Select File (*/*)</button>
                                    (Upload Max fileSize 100MB)
                                    <div data-uploaded-box="upload1" data-ax5uploader-uploaded-box="inline"></div>
                                </div>
                            </ax:td>
                        </ax:tr>
                    </div>
                </form>

                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            투숙 이력 </h2>
                    </div>
                </div>

                <div data-ax5grid="grid-view-02" data-fit-height-content="grid-view-02" style="height: 300px;"></div>
                
            </div>
        </div>
    </div>


    </jsp:body>
</ax:layout>