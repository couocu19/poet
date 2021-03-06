package com.poets.service;

import java.util.List;
import java.util.Map;

public interface PoetService {
    public Map<String,Object> ranShare(Integer rid,Integer uid);
    public Map<String,Object> ranShareList(List<Integer> list, Integer uid);
    public Map<String,Object>selectByKey(String key,Integer uid);
    public Map<String,Object> getAuthor(Integer id);
    public Map<String,Object> getPoet(Integer id,Integer uid);
    public Map<String,Object> getAsPoet(Integer age,String sex);


}
