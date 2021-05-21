package com.fwtai.webflux.service;

import com.alibaba.fastjson.JSONObject;
import com.fwtai.webflux.domain.User;
import com.fwtai.webflux.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;

@Transactional(readOnly = true, transactionManager = "readTransactionManager")
@Service
public class UserService{

    @Autowired
    private userRepository userRepository;

    @Transactional(transactionManager = "writeTransactionManager")
    public Mono<Integer> editUser(final String name,final Long id) {
        return userRepository.editUser(name,id);
    }

    public Mono<User> getUserForId(final Long id){
        return userRepository.getUserForId(id);
    }

    // http://www.cocoachina.com/articles/42607
    public Mono<String> getUser(final Long id){
        final Mono<User> user = userRepository.getUserForId(id);
        Mono<String> mono = user.map((t) -> t.getName());
        return mono;
    }

    public Mono<String> addUser(final String name){
        return json("操作成功,"+name);
    }

    public Mono<String> json(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put("code",200);
        json.put("msg",msg);
        return Mono.justOrEmpty(json.toJSONString());
    }

    public Mono<String> listMap(final Integer age){
        final ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        final HashMap<String,Object> map1 = new HashMap<>();
        map1.put("name","田卓智");
        map1.put("age",age);
        final HashMap<String,Object> map2 = new HashMap<>();
        map2.put("黄女士","田卓智");
        map2.put("age",38);
        list.add(map1);
        list.add(map2);
        return responseJson(list);
    }

    public Mono<String> map(final Integer age){
        final HashMap<String,Object> map = new HashMap<>();
        map.put("name","田卓智");
        map.put("age",age);
        return responseJson(map);
    }

    public Mono<String> responseJson(final Object object){
        if(object == null){
            final JSONObject json = new JSONObject(2);
            json.put("code",201);
            json.put("msg","暂无数据");
            return Mono.justOrEmpty(json.toJSONString());
        }else{
            final JSONObject json = new JSONObject(2);
            json.put("code",200);
            json.put("msg",object);
            return Mono.justOrEmpty(json.toJSONString());
        }
    }
}


class ResultJson<String> extends Mono<String>{

    private String json;

    @Override
    public void subscribe(final CoreSubscriber<? super String> json){
    }
}