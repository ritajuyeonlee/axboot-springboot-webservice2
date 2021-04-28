package edu.axboot.domain.education;

import com.chequer.axboot.core.mybatis.MyBatisMapper;

import java.util.HashMap;
import java.util.List;

public interface EducationJyMapper extends MyBatisMapper{
    List<EducationJy> getByMyBatis(EducationJy educationJy);

    List<EducationJy> select(HashMap<String,String> params);

    EducationJy selectOne(Long id);

    void insert(EducationJy educationJy);

    void update(EducationJy educationJy);

    void delete(Long id);
}
