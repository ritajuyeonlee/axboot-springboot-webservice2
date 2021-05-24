package edu.axboot.controllers.dto.pms.rsv;

import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RsvResponseDto {
    private Long id;
    private Integer sno;
    private Long guestId;
    private Integer nightCnt;
    private Integer adultCnt;
    private Integer chldCnt;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;

    private String rsvDt;
    private String arrDt;
    private String arrTime;
    private String depDt;
    private String depTime;
    private String rsvNum;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String roomTypCd;
    private String roomNum;
    private String saleTypCd;
    private String sttusCd;
    private String srcCd;
    private String payCd;
    private String advnYn;

    private List<ChkMemo> chkMemoList =new ArrayList<ChkMemo>();


    public RsvResponseDto(Chk entity) {
        this.id = entity.getId();
        this.rsvDt =entity.getRsvDt();
        this.sno = entity.getSno();
        this.rsvNum = entity.getRsvNum();
        this.guestId = entity.getGuestId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.langCd = entity.getLangCd();
        this.arrDt = entity.getArrDt();
        this.arrTime = entity.getArrTime();
        this.depDt = entity.getDepDt();
        this.depTime = entity.getDepTime();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd = entity.getRoomTypCd();
        this.roomNum = entity.getRoomNum();
        this.adultCnt = entity.getAdultCnt();
        this.chldCnt = entity.getChldCnt();
        this.saleTypCd = entity.getSaleTypCd();
        this.sttusCd = entity.getSttusCd();
        this.srcCd = entity.getSrcCd();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.payCd = entity.getPayCd();
        this.advnYn = entity.getAdvnYn();
        this.salePrc = entity.getSalePrc();
        this.svcPrc = entity.getSvcPrc();

        this.chkMemoList.addAll(entity.getChkMemoList());
    }
}
