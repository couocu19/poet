package com.poets.service;

import java.util.Map;

public interface CollectService {

    public Map<String,Object> collect(Integer poet_id, Integer user_id);
    public Map<String,Object> remember(Integer collectId,Integer userId);
    public Map<String,Object> cancelRemember(Integer poet_id, Integer user_id);
    public Map<String,Object> getCollects(Integer userId);
    public Map<String,Object> getRemembers(Integer userId);
}
