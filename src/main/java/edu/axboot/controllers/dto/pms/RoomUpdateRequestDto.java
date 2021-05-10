package edu.axboot.controllers.dto.pms;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomUpdateRequestDto {

    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;


    @Builder
    public void EducationUpdateRequestDto(String roomTypCd, String dndYn, String ebYn, String roomSttusCd, String clnSttusCd,
                       String svcSttusCd) {
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn = ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd = clnSttusCd;
        this.svcSttusCd = svcSttusCd;
    }
}
