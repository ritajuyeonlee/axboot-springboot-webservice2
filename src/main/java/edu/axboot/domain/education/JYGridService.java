package edu.axboot.domain.education;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class JYGridService extends BaseService<JYGrid, Long> {
    private JYGridRepository jyGridRepository;

    @Inject
    public JYGridService(JYGridRepository jyGridRepository) {
        super(jyGridRepository);
        this.jyGridRepository = jyGridRepository;
    }

    public List<JYGrid> gets(RequestParams<JYGrid> requestParams) {
        return findAll();
    }

    public List<JYGrid> getByQueryDsl(RequestParams<JYGrid> requestParams) {
        String company = requestParams.getString("company", "");
        String ceo = requestParams.getString("ceo", "");
        String bizno = requestParams.getString("bizno", "");

        List<JYGrid>   list = this.getByQueryDsl(company,ceo,bizno);

        return list;


    }

    private List<JYGrid> getByQueryDsl(String company, String ceo, String bizno) {

        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(company)) {
            builder.and(qjyGrid.companyNm.eq(company));
        }
        if (isNotEmpty(ceo)) {
            builder.and(qjyGrid.ceo.eq(ceo));
        }
        if (isNotEmpty(bizno)) {
            builder.and(qjyGrid.bizno.eq(bizno));
        }
        List<JYGrid> list = select()
                .from(qjyGrid)
                .where(builder)
                .orderBy(qjyGrid.companyNm.asc())
                .fetch();
        return list;

    }
    @Transactional
    public long saveByQueryDsl(List<JYGrid> request) {
        long result=0;
        for (JYGrid jyGrid: request) {
            result = saveOneByQueryDsl(jyGrid);
        }

        return result;
    }

    @Transactional
    public long saveOneByQueryDsl(JYGrid jyGrid) {
        long result=0;
        if (jyGrid.isCreated()) {
            JYGrid rtnObj= save(jyGrid);
            result=rtnObj.getId();
        } else if (jyGrid.isModified()) {
            result= update(qjyGrid)
                    .set(qjyGrid.companyNm, jyGrid.getCompanyNm())
                    .set(qjyGrid.ceo, jyGrid.getCeo())
                    .set(qjyGrid.bizno,jyGrid.getBizno())
                    .where(qjyGrid.id.eq(jyGrid.getId()))
                    .execute();

        } else if (jyGrid.isDeleted()) {
            result= delete(qjyGrid)
                    .where(qjyGrid.id.eq(jyGrid.getId()))
                    .execute();
        }
        return result;

    }

    public JYGrid getOneByQueryDsl(long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qjyGrid.id.eq(id));

        JYGrid jyGrid  = select()
                .from(qjyGrid)
                .where(builder)
                .orderBy(qjyGrid.companyNm.asc())
                .fetchOne();

        return jyGrid;

    }
}