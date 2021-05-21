package edu.axboot.controllers.dto.pms.room;

import edu.axboot.domain.pms.room.Room;
import lombok.Getter;

@Getter
public class RoomListResponseDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;


    public RoomListResponseDto(Room entity) {
        this.id = entity.getId();
        this.roomTypCd = entity.getRoomTypCd();
        this.roomNum = entity.getRoomNum();
        this.dndYn = entity.getDndYn();
        this.ebYn = entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusCd = entity.getClnSttusCd();
        this.svcSttusCd = entity.getSvcSttusCd();


    }

}
