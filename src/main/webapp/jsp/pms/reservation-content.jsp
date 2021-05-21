<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<% 
    RequestUtils requestUtils=RequestUtils.of(request); 
    request.setAttribute("guestNm",requestUtils.getString("guestNm")); 
    request.setAttribute("guestTel",requestUtils.getString("guestTel")); 
    request.setAttribute("email",requestUtils.getString("email")); 
%>
<ax:set key="title" value="${pageName}" />
<ax:set key="page_desc" value="${PAGE_REMARK}" />
<ax:set key="page_auto_height" value="true" />
<ax:set key="axbody_class" value="baseStyle" />

                        <ax:layout name="modal">
                        <jsp:attribute name="script">
                            <ax:script-lang key="ax.script" var="LANG" />
                            <script>
                                var modalParams = { guestNm: "${guestNm}", guestTel: "${guestTel}", email: "${email}" };
                            </script>
                            <script type="text/javascript"
                                src="<c:url value='/assets/js/view/pms/reservation-content.js' />"></script>
                        </jsp:attribute>
                       
                        <jsp:body>

                      <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- 목록 -->
                                        <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                            <div class="left">
                                                <h2><i class="cqc-list"></i>
                                                    투숙객 목록 </h2>
                                            </div>
                            
                                        </div>
                                        <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 160px;"></div>
                            
                                    </div>
                            
                                    <div class="col-md-12">
                                        <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                            <div class="left">
                                                <h2><i class="cqc-list"></i>
                                                    투숙객 정보 </h2>
                                            </div>
                                        </div>
                            
                                        <form name="form" class="js-form">
                            
                                            <div data-ax-tbl="" id="" class="ax-form-tbl" style="">
                                                <div data-ax-tr="" id="" class="" style="">
                                                    
                                                    <div data-ax-td="" style="width:50%;">
                                                        <div data-ax-td-label="" class="" style="width:100px;">이름</div>
                                                        <div data-ax-td-wrap="">
                            
                                                            <input type="text" data-ax-path="guestNm" class="form-control"readonly="readonly">
                            
                                                        </div>
                                                    </div>
                                                    <div data-ax-td="" style="width:50%;">
                                                        <div data-ax-td-label="" class="" style="width:100px;">영문 이름</div>
                                                        <div data-ax-td-wrap="">
                                                    
                                                            <input type="text" data-ax-path="guestNmEng" class="form-control" readonly="readonly">
                                                    
                                                        </div>
                                                    </div>
                            
                                                </div>
                                                <div data-ax-tr="">
                                                    <div data-ax-td="" style="width:50%;">
                                                        <div data-ax-td-label="" style="width:100px;">연락처</div>
                                                        <div data-ax-td-wrap="">
                            
                                                            <input type="text" data-ax-path="guestTel" class="form-control" readonly="readonly">
                            
                                                        </div>
                                                    </div>
                                                    <div data-ax-td="" style="width:50%;">
                                                        <div data-ax-td-label=""  style="width:100px;">이메일</div>
                                                        <div data-ax-td-wrap="">
                            
                                                            <input type="text" data-ax-path="email" class="form-control" readonly="readonly">
                            
                                                        </div>
                                                    </div>
                            
                                                </div>
                                                <div data-ax-tr="" >
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label style="width:100px;">언어</div>
                                                        <div data-ax-td-wrap>
                                                            <ax:common-code groupCd="PMS_LANG" id="langCd" emptyText="전체" />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label style="width:100px;">생년월일</div>
                                                        <div data-ax-td-wrap>
                                                            <input type="date" data-ax-path="brth" class="form-control W150" style="display:inline-block;">
                                                            <input type="radio" data-ax-path="gender" value="M"> 남
                                                            <input type="radio" data-ax-path="gender" value="F"> 여
                                                        </div>
                                                    </div>
                            
                                                </div>
                            
                                                <div data-ax-tr="" id="" class="" style="">
                            
                                                    <div data-ax-td="" id="" class="" style="width:50%;">
                                                        <div data-ax-td-label="" class="" style="width:100px;">비고</div>
                                                        <div data-ax-td-wrap="">
                            
                                                            <textarea name="rmk" data-ax-path="rmk" rows="2" class="form-control" readonly="readonly"></textarea>
                            
                                                        </div>
                                                    </div>
                                                </div> 
                                            </div>
                                        </form>                                                        
                                    </div>
                                </div>
                            </div>

                            <ax:page-buttons>
                                <button type="button" class="btn btn-default" data-page-btn="close"> 닫기 </button>
                                <button type="button" class="btn btn-info" data-page-btn="save"> 선택 </button>
                            </ax:page-buttons>

                        </jsp:body>
                    </ax:layout>