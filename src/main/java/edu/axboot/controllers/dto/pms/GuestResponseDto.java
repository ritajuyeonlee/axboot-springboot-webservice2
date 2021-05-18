package edu.axboot.controllers.dto.pms;

import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.guest.Guest;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuestResponseDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;

    private List<Chk> chkList = new ArrayList<Chk>();

    public GuestResponseDto(Guest entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.langCd = entity.getLangCd();
        this.rmk = entity.getRmk();

        this.chkList.addAll(entity.getChkList());   // 배열 선언 후 add 해야함
    }
}
