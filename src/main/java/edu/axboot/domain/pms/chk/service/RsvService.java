package edu.axboot.domain.pms.chk.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.pms.rsv.RsvListResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chk.ChkRepository;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import edu.axboot.domain.pms.chkMemo.ChkMemoRepository;
import edu.axboot.domain.pms.guest.Guest;
import edu.axboot.domain.pms.guest.GuestRepository;
import edu.axboot.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RsvService extends BaseService<Chk, Long> {
    private final ChkRepository chkRepository;
    private final GuestRepository guestRepository;
    private final ChkMemoRepository chkMemoRepository;


    @Transactional
    public long save(RsvSaveRequestDto saveDto) {
        long id = 0;
        long guestId = 0;

        //투숙객 처리
        if(saveDto.getGuestId()==null || saveDto.getGuestId() == 0 ){
            Guest guest = Guest.builder()
                    .guestNm(saveDto.getGuestNm())
                    .guestNmEng(saveDto.getGuestNmEng())
                    .email(saveDto.getEmail())
                    .langCd(saveDto.getLangCd())
                    .brth(saveDto.getBrth())
                    .gender(saveDto.getGender())
                    .guestTel(saveDto.getGuestTel())
                    .build();
            guestId = guestRepository.save(guest).getId();
        } else if (saveDto.getGuestId() > 0 && saveDto.getGuestNm()!=null) {
            guestId = saveDto.getGuestId();
            Guest guest = Guest.builder()
                    .id(guestId)
                    .guestNm(saveDto.getGuestNm())
                    .guestNmEng(saveDto.getGuestNmEng())
                    .email(saveDto.getEmail())
                    .langCd(saveDto.getLangCd())
                    .brth(saveDto.getBrth())
                    .gender(saveDto.getGender())
                    .guestTel(saveDto.getGuestTel())
                    .build();
            guestRepository.save(guest);
        }

        //SNO,RSVNUM 설정하기
        String rsvDt = LocalDate.now().toString();
        Chk todayLastChk = select().select(
                Projections.fields(Chk.class, qChk.sno))
                .from(qChk)
                .where(qChk.rsvDt.eq(rsvDt))
                .orderBy(qChk.sno.desc())
                .fetchFirst();
        int sno = 1;
        if (todayLastChk != null) {
            sno = todayLastChk.getSno() + 1;
        }

        Chk chk = saveDto.toEntity();
        chk.setGuestId(guestId);
        chk.rsvNumGenerator(rsvDt, sno);
        String rsvNum = chk.getRsvNum();
        id = chkRepository.save(chk).getId();


        //투숙메모 처리
        List<ChkMemo> chkMemoList =saveDto.getChkMemoList();
        for (ChkMemo chkMemo : chkMemoList){
            if (chkMemo.is__created__()) {
                ChkMemo lastChkMemo = select().select(
                        Projections.fields(ChkMemo.class, qChkMemo.sno))
                        .from(qChkMemo)
                        .where(qChkMemo.rsvNum.eq(rsvNum))
                        .orderBy(qChkMemo.sno.desc())
                        .fetchFirst();

                int snoMemo = 1;
                if (lastChkMemo != null) {
                    snoMemo = lastChkMemo.getSno() + 1;
                }

                ChkMemo memo = ChkMemo.builder()
                        .rsvNum(rsvNum)
                        .sno(snoMemo)
                        .memoCn(chkMemo.getMemoCn())
                        .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                        .memoMan(SessionUtils.getCurrentLoginUserCd())
                        .delYn("N")
                        .build();
                chkMemoRepository.save(memo);
            } else if (chkMemo.is__modified__()) {
                ChkMemo memo = chkMemoRepository.findOne(chkMemo.getId());
                memo.update(chkMemo.getMemoCn());
            } else if (chkMemo.is__deleted__()) {
                ChkMemo memo = chkMemoRepository.findOne(chkMemo.getId());
                memo.delete();
            }
        }

        return id;
    }

    @Transactional(readOnly = true)
    public List<RsvListResponseDto>findBy(String guestNm,String roomTypCd,List <String> sttusCds,String sRsvDt,String sDeptDt
            ,String sArrDt,String eRsvDt,String eDeptDt,String eArrDt,String rsvNum) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qChk.guestNm.like("%" + guestNm +"%"));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qChk.roomTypCd.eq(roomTypCd));
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qChk.rsvNum.like("%" + rsvNum +"%"));
        }
        if (isNotEmpty(sttusCds)) {
            for (String sttusCd : sttusCds) {
                builder.and(qChk.sttusCd.eq(sttusCd));
            }
        }
        if (isNotEmpty(sRsvDt)&&isNotEmpty(eRsvDt)) {
            builder.and(qChk.rsvDt.between(sRsvDt,eRsvDt));
        }
        if (isNotEmpty(sDeptDt)&&isNotEmpty(eDeptDt)) {
            builder.and(qChk.depDt.between(sDeptDt,eDeptDt));
        }
        if (isNotEmpty(sArrDt)&&isNotEmpty(eArrDt)) {
            builder.and(qChk.arrDt.between(sArrDt,eArrDt));
        }

        List<Chk> entities = select().select(
                Projections.fields(Chk.class,
                        qChk.id,
                        qChk.sno,
                        qChk.rsvNum,
                        qChk.rsvDt,
                        qChk.guestId,
                        qChk.guestNm,
                        qChk.roomTypCd,
                        qChk.roomNum,
                        qChk.arrDt,
                        qChk.depDt,
                        qChk.saleTypCd,
                        qChk.sttusCd

                ))
                .from(qChk)
                .where(builder)
                .orderBy(qChk.rsvNum.asc())
                .fetch();

        return entities.stream()
                .map(RsvListResponseDto::new)
                .collect(Collectors.toList());
    }

    public RsvResponseDto findById(Long id) {
        Chk chk = chkRepository.findOne(id);
        if (chk == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        return new RsvResponseDto(chk);

    }
}
