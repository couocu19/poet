package com.poets.service.impl;

import com.poets.dao.CollectsMapper;
import com.poets.dao.PoetsMapper;
import com.poets.dao.RememberedMapper;
import com.poets.pojo.Collects;
import com.poets.pojo.Poets;
import com.poets.pojo.Remembered;
import com.poets.service.CollectService;
import com.poets.vo.PoetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectsMapper collectsMapper;
    @Autowired
    private PoetsMapper poetsMapper;
    @Autowired
    private RememberedMapper rememberedMapper;

    public Map<String,Object> collect(Integer poet_id, Integer user_id){
        Map<String,Object> map = new HashMap<>();
        if(poet_id == null || user_id == null){
            map.put("msg","null");
            return map;
        }
        Poets poets = poetsMapper.selectByPrimaryKey(poet_id);
        Collects collects =null;
        if(poets!=null){
            //判断该操作是收藏还是取消收藏
            collects = collectsMapper.selectByUidAndPid(user_id,poet_id);
            if(collects!=null){
                if(collects.getIsCanceled().equals(true)) {
                    collects.setIsCanceled(false);
                    int row = collectsMapper.updateByPrimaryKey(collects);
                    if (row > 0) {
                        map.put("msg", "ok");
                        map.put("info", "canceled");
                        return map;
                    }
                }else{
                    collects.setIsCanceled(true);
                    int row = collectsMapper.updateByPrimaryKey(collects);
                    if (row > 0) {
                        map.put("msg", "ok");
                        map.put("info", collects);
                        return map;
                    }
                }
            }else {
                collects = new Collects();
                collects.setPoetId(poet_id);
                collects.setUserId(user_id);
                collects.setIsCanceled(true);
                //type：1代表唐诗宋词
                collects.setType(1);
                int rowCount = collectsMapper.insertSelective(collects);
                if (rowCount > 0) {
                    map.put("msg", "ok");
                    map.put("info", collects);
                    return map;
                }
            }
        }
        map.put("msg","error-insert");
        return map;

    }

    public Map<String,Object> remember(Integer poetId,Integer userId){
        Map<String,Object> map = new HashMap<>();
        Collects collects = collectsMapper.selectByUidAndPid(userId,poetId);
        if(collects == null){
            map.put("msg","null-collect");
            return map;
        }
        //判断诗词之前有没有背诵过
       /// Integer poet_id = collects.getPoetId();
        Remembered remembered = rememberedMapper.selectByPidAndUid(userId,poetId);
        //说明之前背过
        if (remembered!=null){
            remembered.setIsCanceled(true);
            int rowCount = rememberedMapper.updateByPrimaryKey(remembered);
            if(rowCount>0){
                collects.setIsCanceled(false);
                int row = collectsMapper.updateByPrimaryKey(collects);
                if(row>0){
                    map.put("msg","ok");
                }
            }
        }else{
            remembered = new Remembered();
            remembered.setPoetId(poetId);
            remembered.setUserId(userId);
            remembered.setIsCanceled(true);
            int row = rememberedMapper.insertSelective(remembered);
            if(row>0){
                collects.setIsCanceled(false);
                int row1 = collectsMapper.updateByPrimaryKey(collects);
                if(row1>0) {
                    map.put("msg", "ok");
                    return map;
                }
            }

        }

        map.put("msg","error");
        return map;
    }

    public Map<String,Object> cancelRemember(Integer poet_id, Integer user_id){
        Collects collects = collectsMapper.selectByUidAndPid(user_id,poet_id);
        Remembered remembered = rememberedMapper.selectByPidAndUid(user_id,poet_id);
        Map<String,Object> map = new HashMap<>();
        if(collects == null || remembered == null){
            map.put("msg","null-error");
            return map;
        }

        collects.setIsCanceled(true);
        int row1 = collectsMapper.updateByPrimaryKey(collects);
        if(row1>0){
            remembered.setIsCanceled(false);
            int row2 = rememberedMapper.updateByPrimaryKey(remembered);
            if(row2>0){
                map.put("msg","ok");
                return map;
            }
        }

        map.put("msg","error");
        return map;
    }

    public Map<String,Object> getCollects(Integer userId){
        Map<String,Object> map = new HashMap<>();
        List<Collects> list = collectsMapper.selectByUserId(userId);
        if(list.size() == 0){
            map.put("msg","ok");
            map.put("poet",null);
            return map;
        }
        List<Poets> poets = new ArrayList<>();
        for(Collects collects:list){
            Integer poetId = collects.getPoetId();
            Poets p = poetsMapper.selectByPrimaryKey(poetId);
            poets.add(p);
        }
        List<PoetVo> result = assembleList(poets);
        map.put("msg","ok");
        map.put("poet",result);
        return map;
    }

    public Map<String,Object> getRemembers(Integer userId){
        Map<String,Object> map = new HashMap<>();
        List<Remembered> list = rememberedMapper.selectByUserId(userId);
        if(list.size() == 0){
            map.put("msg","ok");
            map.put("poet",null);
            return map;
        }
        List<Poets> poets = new ArrayList<>();
        for(Remembered r:list){
            Integer poetId = r.getPoetId();
            Poets p = poetsMapper.selectByPrimaryKey(poetId);
            poets.add(p);
        }
        List<PoetVo> result = assembleList(poets);
        map.put("msg","ok");
        map.put("poet",result);
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
}
