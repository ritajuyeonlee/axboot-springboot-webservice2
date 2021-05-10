package edu.axboot.domain.education;

import com.chequer.axboot.core.api.ApiException;
import com.chequer.axboot.core.utils.CoreUtils;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.domain.file.CommonFile;
import edu.axboot.domain.file.CommonFileService;
import edu.axboot.domain.file.UploadParameters;
import edu.axboot.fileupload.FileUploadService;
import edu.axboot.fileupload.UploadFile;
import org.apache.commons.io.FileUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import edu.axboot.domain.BaseService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EducationJyService extends BaseService<EducationJy, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EducationJyService.class);

    @Autowired
    private CommonFileService commonFileService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private EducationJyRepository educationJyRepository;

   /* @Inject*/
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
    public void saveUsingQueryDsl(EducationJy entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            if (entity.getFileIdList().size() > 0) {
                entity.setAttachId(CoreUtils.getUUID().replaceAll("-",""));
                List<CommonFile> commonFileList = new ArrayList<>();
                for(Long fileId: entity.getFileIdList()){
                    CommonFile commonFile = commonFileService.findOne(fileId);
                    commonFile.setTargetId(entity.getAttachId());
                    commonFileList.add(commonFile);
                }
                entity.setFileList(commonFileList);
            }
            this.educationJyRepository.save(entity);
        } else {
            update(qEducationJy)
                    .set(qEducationJy.companyNm, entity.getCompanyNm())
                    .set(qEducationJy.ceo, entity.getCeo())
                    .set(qEducationJy.bizno, entity.getBizno())
                    .set(qEducationJy.tel, entity.getTel())
                    .set(qEducationJy.zip, entity.getZip())
                    .set(qEducationJy.address, entity.getAddress())
                    .set(qEducationJy.addressDetail, entity.getAddressDetail())
                    .set(qEducationJy.email, entity.getEmail())
                    .set(qEducationJy.remark, entity.getRemark())
                    .set(qEducationJy.useYn, entity.getUseYn())
                    .where(qEducationJy.id.eq(entity.getId()))
                    .execute();
        }
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

        if(StringUtils.isEmpty(useYn)&& !"".equals(useYn)&&!"Y".equals(useYn)&&!"N".equals(useYn)){
            throw new RuntimeException("Y or N 입력해!!");
        }

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





    //Excel
    @Transactional
    public String saveDataByExcel(UploadFile uploadFile) throws Exception {
        String resultMsg = "";

        ReaderConfig.getInstance().setSkipErrors(true);

        XLSReader mainReader = ReaderBuilder.buildFromXML(new ClassPathResource("/excel/education_upload.xml").getInputStream());
        List<EducationJy> entities = new ArrayList();

        Map beans = new HashMap();
        beans.put("educationList", entities);

        String excelFile = uploadFile.getSavePath();
        File file = new File(excelFile);
        mainReader.read(FileUtils.openInputStream(file), beans);

        int rowIndex = 1;

        for (EducationJy entity : entities) {
            if (StringUtils.isEmpty(entity.getCompanyNm())) {
                resultMsg = String.format("%d 번째 줄의 회사명이 비어있습니다.", rowIndex);
                throw new ApiException(String.format("%d 번째 줄의 회사명이 비어있습니다.", rowIndex));
            }

            if (StringUtils.isEmpty(entity.getCeo())) {
                resultMsg = String.format("%d 번째 줄의 대표자가 비어있습니다.", rowIndex);
                throw new ApiException(String.format("%d 번째 줄의 대표자가 비어있습니다.", rowIndex));
            }

            if (StringUtils.isEmpty(entity.getUseYn())) {
                resultMsg = String.format("%d 번째 줄의 사용여부가 비어있습니다.", rowIndex);
                throw new ApiException(String.format("%d 번째 줄의 사용여부가 비어있습니다.", rowIndex));
            }

            save(entity);

            rowIndex++;
        }

        return resultMsg;
    }



    @Transactional
    public String uploadFileByExcel(MultipartFile multipartFile) throws Exception {
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setMultipartFile(multipartFile);

        UploadFile uploadFile = fileUploadService.addCommonFile(uploadParameters);
        String result = this.saveDataByExcel(uploadFile);

        fileUploadService.deleteFile(uploadFile.getSavePath());

        return result;
    }

    /*public List<EducationExcelResponseDto> getListExcel(RequestParams<EducationJy> requestParams) {
        List<EducationJy> list = this.getByQueryDsl(requestParams);

        return list.stream()
                .map(EducationExcelResponseDto::new)
                .collect(Collectors.toList());
    }*/
}