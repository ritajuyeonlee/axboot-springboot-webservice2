package edu.axboot.controllers.dto.pms;

import edu.axboot.domain.pms.guest.Guest;
import lombok.Getter;

@Getter
public class GuestListResponseDto {
    private Long id;
    private String guestNm;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;

    public GuestListResponseDto(Guest entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
    }
}
