package edu.axboot.controllers.dto.education;

import edu.axboot.domain.education.book.EducationBook;
import lombok.Getter;

@Getter
public class EducationListResponseDto {
    private Long id;
    private String companyNm;
    private String ceo;
    private String bizno;
    private String useYn;

    public EducationListResponseDto(EducationBook entity) {
        this.id = entity.getId();
        this.companyNm = entity.getCompanyNm();
        this.ceo = entity.getCeo();
        this.bizno = entity.getBizno();
        this.useYn = entity.getUseYn();
    }
}