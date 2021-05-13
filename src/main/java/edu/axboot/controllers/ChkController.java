package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chk.ChkService;

import javax.inject.Inject;
import java.util.List;


@RequiredArgsConstructor
@Controller

public class ChkController extends BaseController {

    @Inject
    private ChkService chkService;

    @GetMapping("/api/v1/chk")
    public Responses.ListResponse list(RequestParams<Chk> requestParams) {
        List<Chk> list = chkService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @PostMapping("/api/v1/chk")
    public ApiResponse save(@RequestBody List<Chk> request) {
        chkService.save(request);
        return ok();
    }
}