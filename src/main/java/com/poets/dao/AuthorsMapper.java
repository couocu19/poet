package com.poets.dao;

import com.poets.pojo.Authors;

import java.util.List;

public interface AuthorsMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Authors record);

    int insertSelective(Authors record);

    Authors selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Authors record);

    int updateByPrimaryKey(Authors record);

    List<Authors> selectByKeyWords(String key);
}