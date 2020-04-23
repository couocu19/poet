package com.poets.dao;

import com.poets.pojo.Remembered;

public interface RememberedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remembered record);

    int insertSelective(Remembered record);

    Remembered selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Remembered record);

    int updateByPrimaryKey(Remembered record);
}