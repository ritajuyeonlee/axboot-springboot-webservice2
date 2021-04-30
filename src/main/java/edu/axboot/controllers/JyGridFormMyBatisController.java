package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.domain.education.EducationJy;
import edu.axboot.domain.education.EducationJyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/education/jyGridFormMyBatis")
public class JyGridFormMyBatisController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EducationJyService.class);

    @Inject
    private EducationJyService educationJyService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "companyNm", required = false) String companyNm,
                                       @RequestParam(value = "ceo", required = false) String ceo,
                                       @RequestParam(value = "bizno", required = false) String bizno,
                                       @RequestParam(value = "useYn", required = false) String useYn) {
        try {
            List<EducationJy> list = educationJyService.select(companyNm,ceo,bizno,useYn);
            return Responses.ListResponse.of(list);

        } catch (BadSqlGrammarException e) {
            logger.error("마이바티스 조회 오류. 쿼리 확인해 보세요~");
            return Responses.ListResponse.of(null);
        }
        catch (RuntimeException e) {
            logger.error(e.getMessage());
            return Responses.ListResponse.of(null);
        }




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