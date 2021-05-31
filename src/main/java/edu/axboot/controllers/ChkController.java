package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.utils.DateUtils;
import com.chequer.axboot.core.utils.ExcelUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.axboot.controllers.dto.pms.rsv.RsvListResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvSaveRequestDto;
import edu.axboot.controllers.dto.pms.sales.SalesResponseDto;
import edu.axboot.domain.pms.chk.service.ChkService;
import edu.axboot.domain.pms.chk.service.RsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChkController extends BaseController {

    @Inject
    private ChkService chkService;

    @Inject
    private RsvService rsvService;

    @GetMapping("/api/v1/rsv")
    public Responses.ListResponse list(@RequestParam(value = "rsvNum", required = false) String rsvNum,
                                       @RequestParam(value = "roomTypCd", required = false) String roomTypCd,
                                       @RequestParam(value = "sttusCd", required = false) List<String> sttusCds,
                                       @RequestParam(value = "sRsvDt", required = false) String sRsvDt,
                                       @RequestParam(value = "sDeptDt", required = false) String sDeptDt,
                                       @RequestParam(value = "sArrDt", required = false) String sArrDt,
                                       @RequestParam(value = "eRsvDt", required = false) String eRsvDt,
                                       @RequestParam(value = "eDeptDt", required = false) String eDeptDt,
                                       @RequestParam(value = "eArrDt", required = false) String eArrDt,
                                       @RequestParam(value = "guestNm", required = false) String guestNm) {
        List<RsvListResponseDto> list = rsvService.findBy(guestNm, roomTypCd, sttusCds, sRsvDt, sDeptDt, sArrDt, eRsvDt, eDeptDt, eArrDt, rsvNum);
        return Responses.ListResponse.of(list);
    }

    @GetMapping("/api/v1/rsv/{id}")
    public RsvResponseDto findById(@PathVariable Long id) {
        return rsvService.findById(id);
    }


    @GetMapping("/api/v1/rsv/sales")
    public Responses.ListResponse list(@RequestParam(value = "sRsvDt", required = false) String sRsvDt,
                                       @RequestParam(value = "eRsvDt", required = false) String eRsvDt) {
        List<SalesResponseDto> list = rsvService.salesStatus(sRsvDt, eRsvDt);
        return Responses.ListResponse.of(list);
    }


    @PostMapping("/api/v1/rsv")
    public ApiResponse save(@RequestBody RsvSaveRequestDto requestDto) {
        rsvService.save(requestDto);
        return ok();
    }

    @ApiOperation(value = "엑셀다운로드", notes = "/resources/excel/pms_sales.xlsx")
    @PostMapping("/api/v1/sales/exceldown")
    public void excelDown(@RequestParam(value = "sRsvDt", required = false) String sRsvDt,
                          @RequestParam(value = "eRsvDt", required = false) String eRsvDt,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<SalesResponseDto> list = rsvService.salesStatus(sRsvDt, eRsvDt);
        ExcelUtils.renderExcel("/excel/pms_sales.xlsx", list, "Sales" + DateUtils.getYyyyMMddHHmmssWithDate(), request, response);
    }
}