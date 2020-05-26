package com.poets.dao;

import com.poets.pojo.Collects;
import io.swagger.models.auth.In;

import java.util.List;

public interface CollectsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collects record);

    int insertSelective(Collects record);

    Collects selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collects record);

    int updateByPrimaryKey(Collects record);

    Collects selectByUidAndPid(Integer userId, Integer poetId);

    Collects selectByUidAndPid1(Integer userId,Integer poetId);

    List<Collects> selectByUserId(Integer userId);
}