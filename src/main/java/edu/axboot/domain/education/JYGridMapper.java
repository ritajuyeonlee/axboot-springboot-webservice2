package edu.axboot.domain.education;

import com.chequer.axboot.core.mybatis.MyBatisMapper;

import java.util.List;

public interface JYGridMapper extends MyBatisMapper{
    List<JYGrid> getByMyBatis(JYGrid jyGrid);
}
