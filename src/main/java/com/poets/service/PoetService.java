package com.poets.service;

import java.util.Map;

public interface PoetService {
    public Map<String,Object> ranShare(Integer rid);
    public Map<String,Object>selectByKey(String key);
    public Map<String,Object> getAuthor(Integer id);
    public Map<String,Object> getPoet(Integer id);


}
