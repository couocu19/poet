package com.poets.dao;

import com.poets.pojo.Clothes;

public interface ClothesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clothes record);

    int insertSelective(Clothes record);

    Clothes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clothes record);

    int updateByPrimaryKey(Clothes record);
}