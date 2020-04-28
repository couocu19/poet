package com.poets.dao;

import com.poets.pojo.Poets;

public interface PoetsMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Poets record);

    int insertSelective(Poets record);

    Poets selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Poets record);

    int updateByPrimaryKey(Poets record);
}