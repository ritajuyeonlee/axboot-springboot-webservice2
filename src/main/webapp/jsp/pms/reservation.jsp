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
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">도착일자</div>
                                    <div data-ax-td-wrap="">
                                        <input type="date" data-ax-path="arrDt" class="form-control" name="trip-start" min="2000-01-01" max="2022-12-31">
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">숙박일수</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="nightCnt" class="form-control"  >
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">출발일자</div>
                                    <div data-ax-td-wrap="">
                                
                                        <input type="date" data-ax-path="depDt" class="form-control" name="trip-start" min="2000-01-01" max="2022-12-31">
                                
                                    </div>
                                </div>
        
                            </div>
                            <div data-ax-tr="" id="" class="" style="">
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">객실타입</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="companyNm" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">성인수</div>
                                    <div data-ax-td-wrap="">
        
                                        <input type="text" data-ax-path="ceo" class="form-control" value="">
        
                                    </div>
                                </div>
                                <div data-ax-td="" id="" class="" style="width:30%;">
                                    <div data-ax-td-label="" class="" style="width:150px;">아동수</div>
                                    <div data-ax-td-wrap="">
                                
                                        <input type="text" data-ax-path="ceo" class="form-control" value="">
                                
                                    </div>
                                </div>
        
                            </div>                         
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label style="width:150px;">
                                        <div tr >투숙객</div>
                                        <div tr > <button type="button" class="btn btn-default" data-grid-view-01-btn="guestsearch">
                                                <i class="cqc-circle-with-plus"></i> 검색 </button></div>
                                    </div>
                            
                                    <div wrap>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
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
                                                    <ax:common-code groupCd="PMS_LANG" clazz="js-roomTypCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">생년월일</div>
                                                <div data-ax-td-wrap>
                                                <input type="date" data-ax-path="brth" class="form-control W100" style="display:inline-block;" name="brth">
                                                &nbsp;
                                                &nbsp;
                                                <input type="radio" name="gender" data-ax-path="gender" value="male"> 남
                                                 <input type="radio" name="gender" data-ax-path="gender" value="femail"> 여
                                                </div>
                                            </div>
                                        </div>
                            
                            
                                    </div>
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
                                                    <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">예약경로</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="" data-ax-path="" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                            
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제방법</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">선수금여부</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" name="email" data-ax-path="email" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:100px;">결제금액</div>
                                                <div data-ax-td-wrap>
                                                    <ax:common-code groupCd="PMS_LANG" clazz="js-roomTypCd" emptyText="전체" />
                                                </div>
                                            </div>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px;">서비스금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="date" data-ax-path="brth" class="form-control W100" name="brth">
                                                    
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
                                            <button type="button" class="btn btn-default" data-grid-view-01-btn="create">
                                                <i class="cqc-erase"></i> 등록
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