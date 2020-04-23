package com.poets.dao;

import com.poets.pojo.Poets;

public interface PoetsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Poets record);

    int insertSelective(Poets record);

    Poets selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Poets record);

    int updateByPrimaryKey(Poets record);
}