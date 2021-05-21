<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <ax:script-lang key="ax.base" var="COL" />
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/reservation.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>
        <div class="container-fluid">
            <div class="row">
                <div>
                    <form name="form" class="js-form">
                        <div data-ax-tbl=""  class="ax-form-tbl" >
                            <div data-ax-tr="" >
                                <div data-ax-td=""  style="width:30%;">
                                    <div data-ax-td-label=""style="width:150px;">도착일자</div>
                                    <div data-ax-td-wrap="">
                                        <input type="date" data-ax-path="arrDt" class="form-control" title='도착일자' data-ax-validate="required">
                                    </div>
                                </div>
                                <div data-ax-td="" style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">숙박일수</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="nightCnt" class="form-control"  >
        
                                    </div>
                                </div>
                                <div data-ax-td=""  style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">출발일자</div>
                                    <div data-ax-td-wrap="">
                                
                                        <input type="date" data-ax-path="depDt" class="form-control"  title='출발일자'  data-ax-validate="required" >
                                
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr=""  >
                                <div data-ax-td="" style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">객실타입</div>
                                    <div data-ax-td-wrap="">
        
                                        <ax:common-code groupCd="PMS_ROOM_TYPE" dataPath="roomTypCd" clazz="js-roomType;" emptyText="전체" />
        
                                    </div>
                                </div>
                                <div data-ax-td=""  style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">성인수</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="number" data-ax-path="adultCnt" class="form-control"  title='성인수'  data-ax-validate="required">
        
                                    </div>
                                </div>
                                <div data-ax-td=""  style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">아동수</div>
                                    <div data-ax-td-wrap="">
                                
                                        <input type="number" data-ax-path="chldCnt" class="form-control"  title='아동수'  data-ax-validate="required">
                                
                                    </div>
                                </div>
        
                            </div>                         
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%" >
                                    <div data-ax-td-label style="width:150px;">
                                        <div  style="padding: 10px;">투숙객</div>
                                        <div  > <button type="button" class="btn btn-default" data-form-view-01-btn="guestsearch" >
                                                <i class="cqc-circle-with-plus"></i> 검색 </button></div>
                                    </div>
                            
                                    <form name="form" class="js-form">
                                        <div data-ax-tbl="" id="" class="ax-form-tbl" >
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:25%">
                                                <div data-ax-td-label style="width:100px;">guestId</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="guestId" data-ax-path="guestId" class="form-control" readonly='readonly'/>
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:25%">
                                                <div data-ax-td-label style="width:100px;">이름</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">영문</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                            
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">연락처</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">이메일</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="email" data-ax-path="email" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">언어</div>
                                                <div data-ax-td-wrap>
                                                    <ax:common-code groupCd="PMS_LANG" name="langCd" dataPath="langCd" clazz="js-langCd;" />
                                                </div>
                                            </div>
                                            
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">생년월일</div>
                                                <div data-ax-td-wrap>
                                                    <input type="date" data-ax-path="brth" name="brth" class="form-control W150" style="display:inline-block;" >
                                                    
                                                      
                                                        <input type="radio" name="gender" data-ax-path="gender" value="M"> 남
                                                        <input type="radio" name="gender" data-ax-path="gender" value="F"> 여
                                                    
                                                </div>
                                            </div>
                                        </div>
                                     </div>
                            
                                    </form>
                                </div>
                            
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label style="width:150px;">
                                        <div tr>판매/결제</div>
                                       
                                    </div>
                            
                                    <div wrap>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">판매유형</div>
                                                <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_SALE_TYPE" clazz="js-saleTypCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">예약경로</div>
                                                <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_RESERVATION_ROUTE" clazz="js-srcCd" emptyText="전체" />
                                                </div>
                                            </div>
                                        </div>
                            
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제방법</div>
                                                <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_PAY_METHOD" clazz="js-payCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">선수금여부</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="advnYn" data-ax-path="advnYn" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="salePrc" data-ax-path="salePrc" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">서비스금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="svcPrc" data-ax-path="svcPrc" class="form-control" />
                                                    
                                                </div>
                                            </div>
                                        </div>
                            
                            
                                    </div>
                                </div>
                            
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label style="width:150px;">
                                        <div tr>투숙메모</div>
                                    </div>

                                    <!-- 목록 -->
                                    <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                        <div class="left">
                                            <h2><i class=""></i> 투숙메모 </h2>
                                        </div>
                                        <div class="right">
                                            <button type="button" class="btn btn-default" data-grid-view-01-btn="add">
                                                <i class="cqc-erase"></i> 등록
                                            </button>
                                            <button type="button" class="btn btn-default" data-grid-view-01-btn="delete">
                                                <i class="cqc-erase"></i> 삭제
                                            </button>
                                        </div>
                                    </div>
                                    <div wrap>
                                        <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
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