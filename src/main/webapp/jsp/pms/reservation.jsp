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
                                        <div class="input-group" data-ax5picker="arrDt">
                                            <input type="text" class="form-control" data-ax-path="arrDt" title='도착일자' data-ax-validate="required"
                                                placeholder="yyyy.mm.dd">
                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                        </div>
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
                                        <div class="input-group" data-ax5picker="depDt">
                                            <input type="text" class="form-control" data-ax-path="depDt" title='출발일자' data-ax-validate="required"  placeholder="yyyy.mm.dd">
                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr=""  >
                                <div data-ax-td="" style="width:30%;">
                                    <div data-ax-td-label="" style="width:150px;">객실타입</div>
                                    <div data-ax-td-wrap="">
        
                                        <ax:common-code groupCd="PMS_ROOM_TYPE" dataPath="roomTypCd"  emptyText="전체" />
        
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
                                                    <input type="text" data-ax-path="guestId" class="form-control" readonly='readonly'/>
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:25%">
                                                <div data-ax-td-label style="width:100px;">이름</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" data-ax-path="guestNm" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">영문</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text"  data-ax-path="guestNmEng" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                            
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">연락처</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text"  data-ax-path="guestTel" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">이메일</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text"data-ax-path="email" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">언어</div>
                                                <div data-ax-td-wrap>
                                                    <ax:common-code groupCd="PMS_LANG" dataPath="langCd" clazz="js-langCd;" />
                                                </div>
                                            </div>
                                            
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">생년월일</div>
                                                <div data-ax-td-wrap>
                                                    <div class="input-group" data-ax5picker="brth" style="display:inline-block;">
                                                        <input type="text" class="form-control  W150" data-ax-path="brth"  data-ax-validate="required"
                                                            placeholder="yyyy.mm.dd">
                                                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                    </div>
                                                        <input type="radio" data-ax-path="gender" value="M"> 남
                                                        <input type="radio"  data-ax-path="gender" value="F"> 여
                                                    
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
                                        <ax:common-code groupCd="PMS_SALE_TYPE" dataPath="saleTypCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">예약경로</div>
                                                <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_RESERVATION_ROUTE" dataPath="srcCd" emptyText="전체" />
                                                </div>
                                            </div>
                                        </div>
                            
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제방법</div>
                                                <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_PAY_METHOD" dataPath="payCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">선수금여부</div>
                                                <div data-ax-td-wrap>
                                                    <input type="radio"  data-ax-path="advnYn" value="Y">Y
                                                    <input type="radio" data-ax-path="advnYn" value="N">N
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" data-ax-path="salePrc" class="form-control" value="0" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">서비스금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" data-ax-path="svcPrc" class="form-control" value="0"/>
                                                    
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