package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.domain.education.EducationJy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.education.EducationJyService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/education/jyGrid")
public class JyGridController extends BaseController {

    @Inject
    private EducationJyService educationJyService;


    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyNm", value = "회사명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ceo", value = "대표자", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bizno", value = "사업자번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", dataType = "String", paramType = "query")
    })
    public Responses.ListResponse list(RequestParams<EducationJy> requestParams) {
        List<EducationJy> list = educationJyService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<EducationJy> request) {
        educationJyService.saveByQueryDsl(request);
        return ok();
    }
    @RequestMapping(value = "/queryDsl", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyNm", value = "회사명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ceo", value = "대표자", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bizno", value = "사업자번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", dataType = "String", paramType = "query")
    })
    public Responses.ListResponse list2(RequestParams<EducationJy> requestParams) {
        List<EducationJy> list = educationJyService.getByQueryDsl(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/queryDsl", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save2(@RequestBody List<EducationJy> request) {
        educationJyService.saveByQueryDsl(request);
        return ok();
    }

    @RequestMapping(value = "/myBatis", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyNm", value = "회사명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ceo", value = "대표자", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bizno", value = "사업자번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", dataType = "String", paramType = "query")
    })
    public Responses.ListResponse list3(RequestParams<EducationJy> requestParams) {
        List<EducationJy> list = educationJyService.getByMyBatis(requestParams);
        return Responses.ListResponse.of(list);
    }




    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "페이지번호(0부터시작)", required = true, dataType = "integer", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "페이지크기", required = true, dataType = "integer", paramType = "query", defaultValue = "50"),
            @ApiImplicitParam(name = "companyNm", value = "회사명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ceo", value = "대표자", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bizno", value = "사업자번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용유무", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/pages", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse pages(RequestParams<EducationJy> requestParams) {
        Page<EducationJy> pages = educationJyService.getPage(requestParams);
        return Responses.PageResponse.of(pages);
    }




}