<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/reservation.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>
        
        
        
        <div class="container-fluid">
            <div class="row">
               
        
                <div>
                    <form name="form" class="js-form">
                        <div data-ax-tbl="" id="" class="ax-form-tbl" style="">
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">ID</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="id" class="form-control" value="" readonly="readonly">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">사용여부</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="useYn" class="form-control" value="">
        
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">회사명</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="companyNm" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">대표</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="ceo" class="form-control" value="">
        
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">사업자번호</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="bizno" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">전화번호</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="tel" class="form-control" value="">
        
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">우편번호</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="id" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">주소</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="address" class="form-control" value="">
        
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">주소 상세</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="addressDetail" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">비고</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="useYn" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="첨부파일" class='icon-info-circled cp' style="width:150px;">비고</div>
                                    <div data-ax-td-wrap="">
        
                                        <div data-ax5uploader="upload1">
                                            <input type="hidden" id="targetType" name="targetType" value="EDUCATION_TEACH" />
                                            <button data-ax5uploader-button="selector" class="btn btn-primary">Select File
                                                (*/*)</button>
        
                                            <div data-uploaded-box="upload1" data-ax5uploader-uploaded-box="inline"></div>
                                        </div>
        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</ax:layout>