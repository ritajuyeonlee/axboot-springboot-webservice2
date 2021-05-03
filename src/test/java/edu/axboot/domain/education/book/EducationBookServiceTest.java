package edu.axboot.domain.education.book;
import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.EducationResponseDto;
import edu.axboot.controllers.dto.EducationSaveRequestDto;
import edu.axboot.controllers.dto.EducationUpdateRequestDto;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationBookServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EducationBookServiceTest.class);

    @Autowired
    private EducationBookService educationBookService;
    public static long testId = 0;


    @Test
    public void test1_거래처_한건_저장() {
        //given
        EducationSaveRequestDto saveRequestDto = EducationSaveRequestDto
                .builder()
                .companyNm("단위테스트")
                .ceo("단위")
                .build();


        //when
       testId = this.educationBookService.save(saveRequestDto);
        logger.info("ID =====================> " + testId);

        //then
        assertTrue(testId >0);
    }

    @Test
    public void test2_거래처_한건_불러오기() {
        //given
       long id = testId;

        //when
       EducationResponseDto result = this.educationBookService.findById(id);
        logger.info(id + " ===================== " + result.getId());

        //then
        assertTrue(id ==result.getId());
    }

    @Test
    public void test3_거래처_한건_수정하고_확인하기() {
        //given
        EducationUpdateRequestDto updateRequestDto = EducationUpdateRequestDto
                .builder()
                .tel("010444")
                .email("aj@jave.com")
                .build();
        //when
        long result = educationBookService.update(testId,updateRequestDto);

        EducationResponseDto res = this.educationBookService.findById(testId);


        //then
        assertTrue(res.getTel().equals(updateRequestDto.getTel())
                &&res.getEmail().equals(updateRequestDto.getEmail()));
    }



}