package edu.axboot.domain.pms.chk.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.pms.rsv.RsvListResponseDto;
import edu.axboot.controllers.dto.pms.rsv.RsvSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chk.ChkRepository;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import edu.axboot.domain.pms.chkMemo.ChkMemoRepository;
import edu.axboot.domain.pms.guest.Guest;
import edu.axboot.domain.pms.guest.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        //TODO 투숙객 처리
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

        //TODO SNO,RSVNUM 설정하기
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
        id = chkRepository.save(chk).getId();



        //TODO 투숙메모 처리
        List<ChkMemo> chkMemoList =saveDto.getChkMemoList();
        for (ChkMemo chkMemo : chkMemoList){
            chkMemoRepository.save(chkMemo);
        }

        return id;
    }


    @Transactional(readOnly = true)
    public List<RsvListResponseDto> findBy(String guestNm, String roomTypCd, String rsvNum) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qChk.guestNm.like("%" + guestNm +"%"));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qChk.roomTypCd.like("%" + roomTypCd +"%"));
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qChk.rsvNum.like("%" + rsvNum +"%"));
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
}
