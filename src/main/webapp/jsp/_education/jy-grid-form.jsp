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
        <script type="text/javascript"
            src="https://cdn.rawgit.com/ax5ui/ax5ui-uploader/master/dist/ax5uploader.js"></script>
        <script type="text/javascript" src="<c:url value='/assets/plugins-fix/ckeditor/ckeditor.js' />"></script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/_education/jy-grid-form.js' />"></script>
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
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='ax.base.company.name' width="200px">
                            <input type="text" name="companyNm" class="js-companyNm form-control" />
                        </ax:td>
                        <ax:td label='ax.base.company.ceo' width="200px">
                            <input type="text" name="ceo" class="js-ceo form-control" />
                        </ax:td>
                        <ax:td label='ax.base.company.bizno' width="200px">
                            <input type="text" name="bizno" class="js-bizno form-control" />
                        </ax:td>
                        <ax:td label='ax.base.use.or.not' width="200px">
                            <ax:common-code groupCd="USE_YN" clazz="js-useYn" emptyText="??????" />
                        </ax:td>
                    </ax:tr>
                   
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <!-- ?????? -->
                    <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                        <div class="left">
                            <h2><i class="cqc-list"></i>
                                ???????????? ?????? </h2>
                        </div>
                        <div class="right">
                            <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i>
                                ??????</button>
                            <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i
                                    class="cqc-circle-with-plus"></i> ??????</button>
                        </div>
                    </div>
                    <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
                    <form name="excelForm" method="post">
                    </form>


                </div>

                <div class="col-md-6">
                    <form name="form" class="js-form" >
                        <div data-ax-tbl="" id="" class="ax-form-tbl" style="">
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class=""style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">ID</div>
                                    <div data-ax-td-wrap="">
                        
                                        <input type="text" data-ax-path="id" class="form-control" value="" readonly="readonly">
                        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class=""style="width:150px;">????????????</div>
                                    <div data-ax-td-wrap="">
                        
                                        <input type="text" data-ax-path="useYn" class="form-control" value="" >
                        
                                    </div>
                                </div>
                        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">?????????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="companyNm" class="form-control" value="" >
                            
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">??????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="ceo" class="form-control" value="" >
                            
                                    </div>
                                </div>
                            
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class=""style="width:150px;">???????????????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="bizno" class="form-control" value="" >
                            
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">????????????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="tel" class="form-control" value="" >
                            
                                    </div>
                                </div>
                            
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">????????????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="id" class="form-control" value="" >
                            
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">??????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="address" class="form-control" value="" >
                            
                                    </div>
                                </div>
                            
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">?????? ??????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <input type="text" data-ax-path="addressDetail" class="form-control" value="" >
                            
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">??????</div>
                                    <div data-ax-td-wrap="">
                            
                                        <textarea name="remark" data-ax-path="remark" rows="5" class="form-control"></textarea>
                            
                                    </div>
                                </div>   
                                <div data-ax-td="" id="" class="" style="width:50%;">
                                    <div data-ax-td-label="????????????" class='icon-info-circled cp' style="width:150px;">??????</div>
                                    <div data-ax-td-wrap="">
                                
                                        <div data-ax5uploader="upload1">
                                            <input type="hidden" id="targetType" name="targetType" value="EDUCATION_TEACH" />
                                            <button data-ax5uploader-button="selector" class="btn btn-primary">Select File (*/*)</button>
                                            (Upload Max fileSize 100MB)
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