package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.utils.DateUtils;
import com.chequer.axboot.core.utils.ExcelUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.axboot.controllers.dto.pms.GuestListResponseDto;
import edu.axboot.controllers.dto.pms.GuestResponseDto;
import edu.axboot.controllers.dto.pms.GuestSaveRequestDto;
import edu.axboot.controllers.dto.pms.GuestUpdateRequestDto;
import edu.axboot.domain.pms.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController extends BaseController {


    private final GuestService guestService;


    @GetMapping("/api/v1/guest")
    public Responses.ListResponse list(@RequestParam(value = "guestNm", required = false) String guestNm,
                                       @RequestParam(value = "guestTel", required = false) String guestTel,
                                       @RequestParam(value = "email", required = false) String email) {
        List<GuestListResponseDto> list = guestService.findBy(guestNm, guestTel, email);
        return Responses.ListResponse.of(list);
    }

    @PostMapping("/api/v1/guest")
    public ApiResponse save(@RequestBody GuestSaveRequestDto requestDto) {
        guestService.save(requestDto);
        return ok();
    }

    @PutMapping("/api/v1/guest")
    public ApiResponse update(@PathVariable Long id, @RequestBody GuestUpdateRequestDto requestDto) {
        guestService.update(requestDto);
        return ok();
    }

    @GetMapping("/api/v1/guest/{id}")
    public GuestResponseDto findById(@PathVariable Long id) {
        return guestService.findById(id);
    }

    @ApiOperation(value = "엑셀다운로드", notes = "/resources/excel/pms_guest.xlsx")
    @PostMapping("/api/v1/guest/exceldown")
    public void excelDown(@RequestParam(value = "guestNm", required = false) String guestNm,
                          @RequestParam(value = "guestTel", required = false) String guestTel,
                          @RequestParam(value = "email", required = false) String email,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GuestListResponseDto> list = guestService.findBy(guestNm, guestTel, email);
        ExcelUtils.renderExcel("/excel/pms_guest.xlsx", list, "Guest_" + DateUtils.getYyyyMMddHHmmssWithDate(), request, response);
    }



}