package com.poets.dao;

import com.poets.pojo.Poets;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoetsMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Poets record);

    int insertSelective(Poets record);

    Poets selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Poets record);

    int updateByPrimaryKey(Poets record);

    List<Poets> selectByKey(@Param(value = "key") String key);

    List<Poets> selectByKeyName(String key);
}