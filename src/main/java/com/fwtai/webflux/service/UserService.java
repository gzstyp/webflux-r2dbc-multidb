package com.fwtai.webflux.service;

import com.alibaba.fastjson.JSONObject;
import com.fwtai.webflux.domain.User;
import com.fwtai.webflux.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

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

    public Mono<String> addUser(final String name){
        return json("操作成功,"+name);
    }

    public static Mono<String> json(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put("code",200);
        json.put("msg",msg);
        return Mono.justOrEmpty(json.toJSONString());
    }
}


class ResultJson<String> extends Mono<String>{

    private String json;

    @Override
    public void subscribe(final CoreSubscriber<? super String> json){
    }
}