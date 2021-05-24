package edu.axboot.domain.pms.chkMemo;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.domain.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ChkMemoService extends BaseService<ChkMemo, Long> {
    @Autowired
    private ChkMemoRepository chkMemoRepository;

    @Inject
    public ChkMemoService(ChkMemoRepository chkMemoRepository) {
        super(chkMemoRepository);
        this.chkMemoRepository = chkMemoRepository;
    }

    public List<ChkMemo> gets(String rsvNum) {
        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(rsvNum)) {
            builder.and(qChkMemo.rsvNum.eq(rsvNum));
        }
        List<ChkMemo> list = select()
                .from(qChkMemo)
                .where(builder)
                .orderBy(qChkMemo.rsvNum.asc())
                .fetch();
        return list;
    }
}