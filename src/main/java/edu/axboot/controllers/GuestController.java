package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.dto.EducationResponseDto;
import edu.axboot.controllers.dto.EducationUpdateRequestDto;
import edu.axboot.domain.pms.guest.Guest;
import edu.axboot.domain.pms.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController extends BaseController {

    @Inject
    private GuestService guestService;

    @GetMapping("/api/v1/guest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "guestNm", value = "이름", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "guestTel", value = "전화번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "이메일", dataType = "String", paramType = "query")
    })
    public Responses.ListResponse list(RequestParams<Guest> requestParams) {
        List<Guest> list = guestService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @PostMapping("/api/v1/guest")
    public ApiResponse save(@RequestBody Guest request) {
        guestService.save(request);
        return ok();
    }

    @GetMapping("/api/v1/guest/{id}")
    public Guest findById(@PathVariable Long id) {
        return guestService.findById(id);
    }
}