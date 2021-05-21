package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import edu.axboot.domain.pms.chkMemo.ChkMemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChkMemoController extends BaseController {

    @Inject
    private ChkMemoService chkMemoService;

    @GetMapping("/api/v1/chkMemo")
    public Responses.ListResponse list(RequestParams<ChkMemo> requestParams) {
        List<ChkMemo> list = chkMemoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @PostMapping("/api/v1/chkMemo")
    public ApiResponse save(@RequestBody ChkMemo request) {
        chkMemoService.save(request);
        return ok();
    }
}