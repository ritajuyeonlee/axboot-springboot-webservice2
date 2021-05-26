package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.pms.rsv.RsvListResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvSaveRequestDto;
import edu.axboot.domain.pms.chk.service.ChkService;
import edu.axboot.domain.pms.chk.service.RsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
                                       @RequestParam(value = "sttusCd", required = false) String sttusCd,
                                       @RequestParam(value = "sRsvDt", required = false) String sRsvDt,
                                       @RequestParam(value = "sDeptDt", required = false) String sDeptDt,
                                       @RequestParam(value = "sArrDt", required = false) String sArrDt,
                                       @RequestParam(value = "eRsvDt", required = false) String eRsvDt,
                                       @RequestParam(value = "eDeptDt", required = false) String eDeptDt,
                                       @RequestParam(value = "eArrDt", required = false) String eArrDt,
                                       @RequestParam(value = "guestNm", required = false) String guestNm) {
        List<RsvListResponseDto> list = rsvService.findBy(guestNm, roomTypCd, sttusCd, sRsvDt, sDeptDt, sArrDt, eRsvDt, eDeptDt, eArrDt, rsvNum);
        return Responses.ListResponse.of(list);
    }

    @GetMapping("/api/v1/rsv/{id}")
    public RsvResponseDto findById(@PathVariable Long id) {
        return rsvService.findById(id);
    }


   /* @PostMapping("/api/v1/chk")
    public ApiResponse save(@RequestBody List<Chk> request) {
        chkService.save(request);
        return ok();
    }*/

    @PostMapping("/api/v1/rsv")
    public ApiResponse save(@RequestBody RsvSaveRequestDto requestDto) {
        rsvService.save(requestDto);
        return ok();
    }
}