package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.education.JYGrid;
import edu.axboot.domain.education.JYGridService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/education/jyGrid")
public class JYGridController extends BaseController {

    @Inject
    private JYGridService jyGridService;


    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<JYGrid> requestParams) {
        List<JYGrid> list = jyGridService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<JYGrid> request) {
        jyGridService.save(request);
        return ok();
    }
    @RequestMapping(value = "/queryDsl", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "company", value = "회사명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ceo", value = "대표자", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bizno", value = "사업자번호", dataType = "String", paramType = "query")
    })
    public Responses.ListResponse list2(RequestParams<JYGrid> requestParams) {
        List<JYGrid> list = jyGridService.getByQueryDsl(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/queryDsl", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save2(@RequestBody List<JYGrid> request) {
        jyGridService.saveByQueryDsl(request);
        return ok();
    }
    @RequestMapping(value = "/queryDsl/one", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query")
    })
    public JYGrid viewOne(RequestParams<JYGrid> requestParams) {
        JYGrid jyGrid = jyGridService.getOneByQueryDsl(requestParams.getLong("id"));
        return jyGrid;
    }

    @RequestMapping(value = "/queryDsl/one", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveOne(@RequestBody JYGrid request) {
        jyGridService.saveOneByQueryDsl(request);
        return ok();
    }
}