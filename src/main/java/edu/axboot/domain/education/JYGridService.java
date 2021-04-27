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
    private JYGridMapper jyGridMapper;

    @Inject
    public JYGridService(JYGridRepository jyGridRepository) {
        super(jyGridRepository);
        this.jyGridRepository = jyGridRepository;
    }

    public List<JYGrid> gets(RequestParams<JYGrid> requestParams) {
        String type = requestParams.getString("type","");
        if(type.equals("myBatis")){
            return this.getByMyBatis(requestParams);
        }
        else{
            return this.getByQueryDsl(requestParams);
        }
    }

    public List<JYGrid> getByQueryDsl(RequestParams<JYGrid> requestParams) {
        String company = requestParams.getString("company", "");
        String ceo = requestParams.getString("ceo", "");
        String bizno = requestParams.getString("bizno", "");
        String useYn = requestParams.getString("useYn", "");

        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(company)) {
            builder.and(qjyGrid.companyNm.contains(company));
        }
        if (isNotEmpty(ceo)) {
            builder.and(qjyGrid.ceo.contains(ceo));
        }
        if (isNotEmpty(bizno)) {
            builder.and(qjyGrid.bizno.contains(bizno));
        }
        if (isNotEmpty(useYn)) {
            builder.and(qjyGrid.useYn.eq(useYn));
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
            if (jyGrid.isCreated()) {
                JYGrid rtnObj= save(jyGrid);
                result=rtnObj.getId();
            } else if (jyGrid.isModified()) {
                result= update(qjyGrid)
                        .set(qjyGrid.companyNm, jyGrid.getCompanyNm())
                        .set(qjyGrid.ceo, jyGrid.getCeo())
                        .set(qjyGrid.bizno,jyGrid.getBizno())
                        .set(qjyGrid.useYn,jyGrid.getUseYn())
                        .where(qjyGrid.id.eq(jyGrid.getId()))
                        .execute();

            } else if (jyGrid.isDeleted()) {
                result= delete(qjyGrid)
                        .where(qjyGrid.id.eq(jyGrid.getId()))
                        .execute();
            }
        }
        return result;
    }


    public List<JYGrid> getByMyBatis(RequestParams<JYGrid> requestParams) {
        JYGrid jyGrid = new JYGrid();
        jyGrid.setCompanyNm(requestParams.getString("company", ""));
        jyGrid.setCeo(requestParams.getString("ceo", ""));
        jyGrid.setBizno(requestParams.getString("bizno", ""));
        jyGrid.setBizno(requestParams.getString("useYn", ""));

        List<JYGrid> list=this.jyGridMapper.getByMyBatis(jyGrid);
        return list;

    }
}