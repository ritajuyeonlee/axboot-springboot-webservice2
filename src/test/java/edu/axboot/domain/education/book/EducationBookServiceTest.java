package edu.axboot.domain.education.book;
import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.EducationResponseDto;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationBookServiceTest {

    @Autowired
    private EducationBookService educationBookService;
    public static long testId = 0;

    @Test
    public void test1_거래처_한건_불러오기() {
        //given
        Long id = 2L;

        //when
        EducationResponseDto result = this.educationBookService.findById(id);

        //then
        assertTrue(result.getId() == 2L);
    }

}