package edu.axboot.domain.pms.chk;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class ChkService extends BaseService<Chk, Long> {
    private ChkRepository chkRepository;

    @Inject
    public ChkService(ChkRepository chkRepository) {
        super(chkRepository);
        this.chkRepository = chkRepository;
    }

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
    }
}