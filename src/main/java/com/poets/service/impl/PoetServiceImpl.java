package com.poets.service.impl;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.poets.dao.AuthorsMapper;
import com.poets.dao.PoetsMapper;
import com.poets.pojo.Authors;
import com.poets.pojo.Poets;
import com.poets.service.PoetService;
import com.poets.vo.AuthorVo;
import com.poets.vo.PoetVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("poetService")
public class PoetServiceImpl implements PoetService {

    public static final String CACHE_KEY_Poet = "poet:";
    public static final String CACHE_KEY_Author = "author:";

    @Autowired
    private PoetsMapper poetsMapper;
    @Autowired
    private AuthorsMapper authorsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

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

    public Map<String,Object>selectByKey(String key){
        List<Authors> authors = authorsMapper.selectByKeyWords(key);
        List<Poets> poets;
        if(authors.size() == 0) {
            poets = poetsMapper.selectByKey(key);
            System.out.println(poets.size());
        }else{
            poets = poetsMapper.selectByKeyName(key);
        }
        Map<String,Object> map = new HashMap<>();
        if(authors == null && poets == null){
            map.put("msg","暂时没查到任何信息哦~");
        }
        List<AuthorVo> authorVos = assembleAuthor(authors);
        List<PoetVo> poetVos = assembleList(poets);
        map.put("msg","ok");
        map.put("author",authorVos);
        map.put("poets",poetVos);
        return map;
    }

    private List<PoetVo> assembleList(List<Poets> poets){
        List<PoetVo> poetVos = new ArrayList<>();
        PoetVo poetVo = null;
        for(Poets poet:poets){
            poetVo = new PoetVo();
            int id = poet.getSid();
            if(id>=1 && id<=253900){
                poetVo.setDynasty("宋代");
            }else{
                poetVo.setDynasty("唐代");
            }
            poetVo.setAuthor(poet.getAuthor());
            poetVo.setSid(poet.getSid());
            poetVo.setTitle(poet.getTitle());
            int len = poet.getParagraphs().length();
            int t = 0;
            String s = "";
            for(int i =0;i<len;i++){
                if(poet.getParagraphs().charAt(i) == '"'){
                    t++;
                }
                if (t == 2){
                    s = poet.getParagraphs().substring(2,i+1);
                    break;
                }
            }
            poetVo.setParagraphs(s);
            poetVos.add(poetVo);
        }

        return poetVos;
    }

    private List<AuthorVo> assembleAuthor(List<Authors> authors){
        List<AuthorVo> list = new ArrayList<>();
        AuthorVo authorVo = null;
        for(Authors a:authors){
            authorVo = new AuthorVo();
            int mid = a.getMid();
            if(mid>=1 && mid<=8870){
                authorVo.setDynasty("宋代");
            }else{
                authorVo.setDynasty("唐代");
            }
            authorVo.setMid(mid);
            authorVo.setName(a.getName());

            int min = 1;
            int max = 11;
            int ran = (int)(Math.random()*(max-min)+min);
            String image = String.valueOf(ran)+".JPG";
            String header = "http://118.31.12.175:8081/images/"+image;
           // String header = "http://localhost:8081/images/"+image;
            authorVo.setHeader(header);
            list.add(authorVo);
        }
        return list;
    }


    public Map<String,Object> getAuthor(Integer id){
        Map<String,Object> map = new HashMap<>();
        ValueOperations<String,Authors> operations = redisTemplate.opsForValue();
        //缓存key
        String key = CACHE_KEY_Author+id;
        //先去redis查,如果查到直接返回,如果没有的话直接去数据库查
        Authors authors = operations.get(key);

        //redis中无数据,去数据库捞数据
        if(authors == null) {
            authors = authorsMapper.selectByPrimaryKey(id);
            if (authors != null) {
                operations.set(key,authors);
                map.put("msg", "ok");
                map.put("author", authors);
                return map;
            }
        }
        map.put("msg", "ok");
        map.put("author", authors);
        return map;
    }


    public Map<String,Object> getPoet(Integer id){
        Map<String,Object> map = new HashMap<>();
        ValueOperations<String,Poets> operations = redisTemplate.opsForValue();
        String key = CACHE_KEY_Poet+id;
        Poets poets = operations.get(key);
        if(poets == null) {
            poets = poetsMapper.selectByPrimaryKey(id);
            if (poets != null) {
                operations.set(key,poets);
                PoetVo poetVo = assemble(poets);
                map.put("msg", "ok");
                map.put("poet", poetVo);
                return map;
            }
        }
        map.put("msg", "ok");
        map.put("poet",poets);
        return map;
    }



}
