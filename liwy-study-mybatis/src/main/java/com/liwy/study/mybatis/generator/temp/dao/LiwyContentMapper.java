package com.liwy.study.mybatis.generator.temp.dao;

import com.liwy.study.mybatis.generator.temp.entity.LiwyContent;
import com.liwy.study.mybatis.generator.temp.entity.LiwyContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiwyContentMapper {
    long countByExample(LiwyContentExample example);

    int deleteByExample(LiwyContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LiwyContent record);

    int insertSelective(LiwyContent record);

    List<LiwyContent> selectByExampleWithBLOBs(LiwyContentExample example);

    List<LiwyContent> selectByExample(LiwyContentExample example);

    LiwyContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LiwyContent record, @Param("example") LiwyContentExample example);

    int updateByExampleWithBLOBs(@Param("record") LiwyContent record, @Param("example") LiwyContentExample example);

    int updateByExample(@Param("record") LiwyContent record, @Param("example") LiwyContentExample example);

    int updateByPrimaryKeySelective(LiwyContent record);

    int updateByPrimaryKeyWithBLOBs(LiwyContent record);

    int updateByPrimaryKey(LiwyContent record);
}