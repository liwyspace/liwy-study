package com.liwy.study.mybatis.generator.temp.dao;

import com.liwy.study.mybatis.generator.temp.entity.LiwyChannel;
import com.liwy.study.mybatis.generator.temp.entity.LiwyChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiwyChannelMapper {
    long countByExample(LiwyChannelExample example);

    int deleteByExample(LiwyChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LiwyChannel record);

    int insertSelective(LiwyChannel record);

    List<LiwyChannel> selectByExample(LiwyChannelExample example);

    LiwyChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LiwyChannel record, @Param("example") LiwyChannelExample example);

    int updateByExample(@Param("record") LiwyChannel record, @Param("example") LiwyChannelExample example);

    int updateByPrimaryKeySelective(LiwyChannel record);

    int updateByPrimaryKey(LiwyChannel record);
}