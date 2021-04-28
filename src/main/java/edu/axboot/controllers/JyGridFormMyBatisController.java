package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.domain.education.EducationJy;
import edu.axboot.domain.education.EducationJyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/education/jyGridFormMyBatis")
public class JyGridFormMyBatisController extends BaseController {

    @Inject
    private EducationJyService educationJyService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "companyNm", required = false) String companyNm,
                                       @RequestParam(value = "ceo", required = false) String ceo,
                                       @RequestParam(value = "bizno", required = false) String bizno,
                                       @RequestParam(value = "useYn", required = false) String useYn) {
        List<EducationJy> list = educationJyService.select(companyNm,ceo,bizno,useYn);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public EducationJy view(@PathVariable Long id){
        EducationJy educationJY = educationJyService.selectOne(id);

        return educationJY;

    }


    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody EducationJy request) {
        educationJyService.enroll(request);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse remove(@PathVariable Long id){
        educationJyService.del(id);
        return ok();
    }

}