package com.poets.dao;

import com.poets.pojo.Collects;

public interface CollectsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collects record);

    int insertSelective(Collects record);

    Collects selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collects record);

    int updateByPrimaryKey(Collects record);
}