package edu.axboot.domain.education.book;

import edu.axboot.AXBootApplication;
import edu.axboot.domain.education.EducationJyService;
import edu.axboot.fileupload.UploadFile;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertTrue;

@Log
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
public class EducationJyExcelTest {

    @Value("${axboot.upload.repository}")
    public String uploadRepository;

    @Autowired
    private EducationJyService educationJyService;

    @Transactional  //삽입 후 삭제
    @SneakyThrows   //명시적인 예외처리 생략
    @Test
    public void testSaveDataByExcel() {
        // given
        UploadFile uploadFile = new UploadFile();
        uploadFile.setSavePath(uploadRepository + "/test_upload.xlsx");

        // when
        String result = educationJyService.saveDataByExcel(uploadFile);

        // then
        assertTrue(StringUtils.isEmpty(result));
    }

}