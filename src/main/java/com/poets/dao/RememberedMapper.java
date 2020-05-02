package com.poets.dao;

import com.poets.pojo.Remembered;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RememberedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remembered record);

    int insertSelective(Remembered record);

    Remembered selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Remembered record);

    int updateByPrimaryKey(Remembered record);

    Remembered selectByPidAndUid(@Param(value = "userId") Integer userId, @Param(value = "poetId") Integer poetId);

    List<Remembered> selectByUserId(Integer userId);

}