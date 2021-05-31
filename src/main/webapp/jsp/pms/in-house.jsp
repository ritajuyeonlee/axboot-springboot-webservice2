<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

            <ax:set key="title" value="${pageName}" />
            <ax:set key="page_desc" value="${PAGE_REMARK}" />
            <ax:set key="page_auto_height" value="true" />

            <ax:layout name="base">
                <jsp:attribute name="script">

                    <script type="text/javascript"
                        src="<c:url value='/assets/js/view/pms/in-house.js' />"></script>

                </jsp:attribute>
                <jsp:body>

                    <ax:page-buttons></ax:page-buttons>


<div role="page-header">
    <ax:form name="searchView0">
        <ax:tbl clazz="ax-search-tbl" minWidth="500px">
            <ax:tr>
                <ax:td label='예약자' width="300px">
                    <input type="text" class="js-guestNm form-control" />
                </ax:td>
                <ax:td label='예약번호' width="400px">
                    <input type="text" class="js-rsvNum form-control" />
                </ax:td>
                <ax:td label='예약일' width="400px">
                    <div class="input-group" data-ax5picker="date">
                        <input type="text" class="js-sRsvDt form-control" data-ax-path="sRsvDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="js-eRsvDt form-control" data-ax-path="eRsvDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                    </div>
                </ax:td>
            </ax:tr>
            <ax:tr>
                <ax:td label='객실타입' width="300px">
                    <select name="roomTypCd" class="js-roomTypCd">
                        <option value="">전체</option>
                        <option value="SB">SB</option>
                        <option value="DB">DB</option>
                        <option value="DT">DT</option>
                    </select>
                </ax:td>
                
                <ax:td label='도착일' width="400px">
                    <div class="input-group" data-ax5picker="date">
                        <input type="text" class="js-sArrDt form-control" data-ax-path="sArrDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="js-eArrDt form-control" data-ax-path="eArrDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                    </div>
                </ax:td>
                <ax:td label='출발일' width="400px">
                    <div class="input-group" data-ax5picker="date">
                        <input type="text" class="js-sDeptDt form-control" data-ax-path="sDeptDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="js-eDeptDt form-control" data-ax-path="eDeptDt"
                            placeholder="yyyy/mm/dd">
                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                    </div>
                </ax:td>
            </ax:tr>
           
        </ax:tbl>
    </ax:form>
    <div class="H10"></div>
</div>

                    <ax:split-layout name="ax1" orientation="horizontal">
                        <ax:split-panel width="*" style="">

                            <!-- 목록 -->
                            <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                <div class="left">
                                    <h2><i class="cqc-list"></i>
                                        투숙 목록 </h2>
                                </div>
                               
                            </div>
                            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01"
                                style="height: 300px;"></div>

                        </ax:split-panel>
                    </ax:split-layout>

                </jsp:body>
            </ax:layout>