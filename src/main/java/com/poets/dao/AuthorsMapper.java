package com.poets.dao;

import com.poets.pojo.Authors;

public interface AuthorsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Authors record);

    int insertSelective(Authors record);

    Authors selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Authors record);

    int updateByPrimaryKey(Authors record);
}