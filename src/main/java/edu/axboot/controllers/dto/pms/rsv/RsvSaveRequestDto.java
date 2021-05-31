package edu.axboot.controllers.dto.pms.rsv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class RsvSaveRequestDto {
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

    private List<ChkMemo> chkMemoList;
    @Transient
    @JsonProperty("__deleted__")
    protected boolean __deleted__;
    @Transient
    @JsonProperty("__created__")
    protected boolean __created__;
    @Transient
    @JsonProperty("__modified__")
    protected boolean __modified__;

    @Transient
    @JsonIgnore
    public boolean isDeleted() {
        return this.__deleted__;
    }

    @Transient
    @JsonIgnore
    public boolean isCreated() {
        return this.__created__;
    }

    @Transient
    @JsonIgnore
    public boolean isModified() {
        return this.__modified__;
    }


    @Builder
    public RsvSaveRequestDto(Long id, Integer sno, Long guestId, Integer nightCnt, Integer adultCnt, Integer chldCnt, BigDecimal salePrc, BigDecimal svcPrc, String rsvDt, String arrDt, String arrTime, String depDt, String depTime, String rsvNum, String guestNm, String guestNmEng, String guestTel, String email, String brth, String gender, String langCd, String roomTypCd, String roomNum, String saleTypCd, String sttusCd, String srcCd, String payCd, String advnYn) {
        this.id = id;
        this.sno = sno;
        this.guestId = guestId;
        this.nightCnt = nightCnt;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
        this.rsvDt = rsvDt;
        this.arrDt = arrDt;
        this.arrTime = arrTime;
        this.depDt = depDt;
        this.depTime = depTime;
        this.rsvNum = rsvNum;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.brth = brth;
        this.gender = gender;
        this.langCd = langCd;
        this.roomTypCd = roomTypCd;
        this.roomNum = roomNum;
        this.saleTypCd = saleTypCd;
        this.sttusCd = sttusCd;
        this.srcCd = srcCd;
        this.payCd = payCd;
        this.advnYn = advnYn;
    }


    public Chk toEntity() {
        return Chk.builder()
                .id(id)
                .rsvDt(rsvDt)
                .sno (sno)
                .rsvNum(rsvNum)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
                .arrDt (arrDt)
                .arrTime(arrTime)
                .depDt(depDt)
                .depTime(depTime)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .roomNum(roomNum)
                .adultCnt(adultCnt)
                .chldCnt (chldCnt)
                .saleTypCd(saleTypCd)
                .sttusCd(sttusCd)
                .srcCd(srcCd)
                .brth(brth)
                .gender(gender)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)

                .build();
    }
}
