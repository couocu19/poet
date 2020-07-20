package com.poets.dao;

import com.poets.pojo.AsPoet;

public interface AsPoetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AsPoet record);

    int insertSelective(AsPoet record);

    AsPoet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AsPoet record);

    int updateByPrimaryKey(AsPoet record);
}