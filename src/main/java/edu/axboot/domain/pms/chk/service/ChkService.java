package edu.axboot.domain.pms.chk.service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.pms.rsv.RsvSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.pms.chk.Chk;
import edu.axboot.domain.pms.chk.ChkRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Service
public class ChkService extends BaseService<Chk, Long> {
    private ChkRepository chkRepository;

    @Inject
    public ChkService(ChkRepository chkRepository) {
        super(chkRepository);
        this.chkRepository = chkRepository;
    }

    public long save(RsvSaveRequestDto saveDto) {
        long id = 0;
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
        chk.rsvNumGenerator(rsvDt, sno);
//        id = chkRepository.save(chk).getId();
        //TODO 투숙객 처리
        //TODO 투숙메모 처리
        return id;
    }

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
    }
}