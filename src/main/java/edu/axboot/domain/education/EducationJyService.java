package edu.axboot.domain.education;

import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EducationJyService extends BaseService<EducationJy, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EducationJyService.class);


    private EducationJyRepository educationJyRepository;

    @Inject
    private EducationJyMapper educationJyMapper;

    @Inject
    public EducationJyService(EducationJyRepository educationJyRepository) {
        super(educationJyRepository);
        this.educationJyRepository = educationJyRepository;
    }

    //Jpa
    public List<EducationJy> gets(RequestParams<EducationJy> requestParams) {
        /*String type = requestParams.getString("type","");
        if(type.equals("myBatis")){
            return this.getByMyBatis(requestParams);
        }
        else{
            return this.getByQueryDsl(requestParams);
        }*/

        List<EducationJy> list2 = this.getFilter(findAll(), requestParams.getString("companyNm", ""), 1);
        List<EducationJy> list3 = this.getFilter(list2, requestParams.getString("ceo", ""), 2);
        List<EducationJy> list4 = this.getFilter(list3, requestParams.getString("bizno", ""), 3);
        List<EducationJy> list5 = this.getFilter(list4, requestParams.getString("useYn", ""), 4);
        return list5;
    }

    private List<EducationJy> getFilter(List<EducationJy> sources, String filter, int typ) {
        List<EducationJy> targets = new ArrayList<EducationJy>();
        for (EducationJy entity : sources) {
            if ("".equals(filter)) {
                targets.add(entity);
            } else {
                if (typ == 1) {
                    if (entity.getCompanyNm().contains(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 2) {
                    if (entity.getCeo().contains(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 3) {
                    if (entity.getBizno().equals(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 4) {
                    if (entity.getUseYn().equals(filter)) {
                        targets.add(entity);
                    }
                }
            }
        }
        return targets;
    }


    //QueryDsl
    public List<EducationJy> gets(String companyNm, String ceo, String bizno, String useYn) {

        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(companyNm)) {
            builder.and(qEducationJy.companyNm.like("%" + companyNm + "%"));
        }
        if (isNotEmpty(ceo)) {
            builder.and(qEducationJy.ceo.like("%" + ceo + "%"));
        }
        if (isNotEmpty(bizno)) {
            builder.and(qEducationJy.bizno.like("%" + bizno + "%"));
        }
        if (isNotEmpty(useYn)) {
            builder.and(qEducationJy.useYn.eq(useYn));
        }

        List<EducationJy> list = select()
                .from(qEducationJy)
                .where(builder)
                .orderBy(qEducationJy.companyNm.asc())
                .fetch();
        return list;


    }

    public EducationJy getByOne(Long id) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qEducationJy.id.eq(id));

        EducationJy educationJy = select()
                .from(qEducationJy)
                .where(builder)
                .fetchOne();
        //    .fetch(); //list로 리턴

        return educationJy;

        //     return getOne(id); //가능
    }

    public List<EducationJy> getByQueryDsl(RequestParams<EducationJy> requestParams) {
        String companyNm = requestParams.getString("companyNm", "");
        String ceo = requestParams.getString("ceo", "");
        String bizno = requestParams.getString("bizno", "");
        String useYn = requestParams.getString("useYn", "");

        logger.info("회사명 : "+companyNm);
        logger.info("대표자 : "+ceo);
        logger.info("사업자번호 : "+bizno );
        logger.info("사용여부 : "+useYn);


        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(companyNm)) {
            builder.and(qEducationJy.companyNm.contains(companyNm));
        }
        if (isNotEmpty(ceo)) {
            builder.and(qEducationJy.ceo.contains(ceo));
        }
        if (isNotEmpty(bizno)) {
            builder.and(qEducationJy.bizno.contains(bizno));
        }
        if (isNotEmpty(useYn)) {
            builder.and(qEducationJy.useYn.eq(useYn));
        }
        List<EducationJy> list = select()
                .from(qEducationJy)
                .where(builder)
                .orderBy(qEducationJy.companyNm.asc())
                .fetch();
        return list;

    }

    @Transactional
    public long saveByQueryDsl(List<EducationJy> request) {
        long result = 0;
        for (EducationJy educationJy : request) {
            if (educationJy.isCreated()) {
                EducationJy rtnObj = save(educationJy);
                result = rtnObj.getId();
            } else if (educationJy.isModified()) {
                result = update(qEducationJy)
                        .set(qEducationJy.companyNm, educationJy.getCompanyNm())
                        .set(qEducationJy.ceo, educationJy.getCeo())
                        .set(qEducationJy.bizno, educationJy.getBizno())
                        .set(qEducationJy.useYn, educationJy.getUseYn())
                        .where(qEducationJy.id.eq(educationJy.getId()))
                        .execute();

            } else if (educationJy.isDeleted()) {
                result = delete(qEducationJy)
                        .where(qEducationJy.id.eq(educationJy.getId()))
                        .execute();
            }
        }
        return result;
    }

    @Transactional
    public void persist(EducationJy request) {
        if (request.getId() == null || request.getId() == 0) {
            save(request);
        } else {
            update(qEducationJy)
                    .set(qEducationJy.companyNm, request.getCompanyNm())
                    .set(qEducationJy.ceo, request.getCeo())
                    .set(qEducationJy.bizno, request.getBizno())
                    .set(qEducationJy.useYn, request.getUseYn())
                    .where(qEducationJy.id.eq(request.getId()))
                    .execute();
        }

    }

    @Transactional
    public void remove(Long id) {
        if (id != null || id != 0) {
            delete(qEducationJy)
                    .where(qEducationJy.id.eq(id))
                    .execute();
        }
    }


    //MyBatis
    public List<EducationJy> getByMyBatis(RequestParams<EducationJy> requestParams) {
        EducationJy educationJy = new EducationJy();
        educationJy.setCompanyNm(requestParams.getString("companyNm", ""));
        educationJy.setCeo(requestParams.getString("ceo", ""));
        educationJy.setBizno(requestParams.getString("bizno", ""));
        educationJy.setBizno(requestParams.getString("useYn", ""));

        List<EducationJy> list = this.educationJyMapper.getByMyBatis(educationJy);
        return list;

    }

    public List<EducationJy> select(String companyNm, String ceo, String bizno, String useYn) {

        HashMap<String, String> params = new HashMap<String,String>();

        params.put("companyNm",companyNm);
        params.put("ceo",ceo);
        params.put("bizno",bizno);
        params.put("useYn",useYn);

        List<EducationJy> list = educationJyMapper.select(params);

        return list;
    }

    public EducationJy selectOne(Long id) {
        return educationJyMapper.selectOne(id);
    }

    @Transactional
    public void enroll(EducationJy request) {
            if (request.getId() == null || request.getId() == 0) {
                educationJyMapper.insert(request);
            }
            else {
                educationJyMapper.update(request);
            }
    }

    @Transactional
    public void del(Long id) {
        educationJyMapper.delete(id);
    }


    //Paging
    public Page<EducationJy> getPage(RequestParams<EducationJy> requestParams) {
        List<EducationJy> list = this.getList(requestParams);
        Pageable pageable = requestParams.getPageable();
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize() > list.size() ? list.size() : (start + pageable.getPageSize()));
        Page<EducationJy> pages = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return pages;
    }

    public List<EducationJy> getList(RequestParams<EducationJy> requestParams) {
        return getByQueryDsl(requestParams);
    }
}