package com.poets.service.impl;

import com.poets.dao.PoetsMapper;
import com.poets.pojo.Poets;
import com.poets.service.PoetService;
import com.poets.vo.PoetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("poetService")
public class PoetServiceImpl implements PoetService {

    @Autowired
    private PoetsMapper poetsMapper;

    public Map<String,Object> ranShare(Integer rid){
        Map<String,Object> map = new HashMap<>();
        if(rid == null){
            map.put("msg","error");
            return map;
        }

        Poets poets = poetsMapper.selectByPrimaryKey(rid);
        if(poets!=null){
            map.put("msg","ok");
            PoetVo poetVo = assemble(poets);
            map.put("poet",poetVo);
            return map;
        }
        map.put("msg","null");
        return map;
    }

    private PoetVo assemble(Poets poets){
        PoetVo poetVo = new PoetVo();
        int id = poets.getSid();
        if(id>=1 && id<=253900){
            poetVo.setDynasty("宋代");
        }else{
            poetVo.setDynasty("唐代");
        }
        poetVo.setAuthor(poets.getAuthor());
        int len = poets.getParagraphs().length();
        String para = poets.getParagraphs().substring(2,len-1);
        poetVo.setParagraphs(para);
        poetVo.setSid(poets.getSid());
        poetVo.setTitle(poets.getTitle());
        return poetVo;
    }



}
