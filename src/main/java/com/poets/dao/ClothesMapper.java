package com.poets.dao;

import com.poets.pojo.Clothes;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

public interface ClothesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clothes record);

    int insertSelective(Clothes record);

    Clothes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clothes record);

    int updateByPrimaryKey(Clothes record);

    Clothes selectByUid(Integer userId);

    Clothes selectByMap(@Param(value = "userId") Integer userId, @Param(value = "hair")Integer hair,
                    @Param(value = "dress")Integer dress, @Param(value = "back")Integer back, @Param(value = "face")Integer face);




}