package com.fwtai.reactive.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwtai.reactive.domain.User;
import com.fwtai.reactive.repository.userRepository;
import com.fwtai.reactive.tool.ToolClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true, transactionManager = "readTransactionManager")
@Service
public class UserService{

    @Autowired
    private userRepository userRepository;

    @Transactional(transactionManager = "writeTransactionManager")
    public Mono<String> editUser(final String name,final Long id) {
        final Mono<Integer> user = userRepository.editUser(name,id);
        return user.map(ToolClient::executeRows);
    }

    public Mono<User> getUserForId(final Long id){
        return userRepository.getUserForId(id);
    }

    // sql 查询时,不能返回list_Map,也不能返回map,但是可以参考 http://www.cocoachina.com/articles/42607
    public Mono<String> getUser(final Long id){
        final Mono<User> user = userRepository.getUserForId(id);
        Mono<String> mono = user.map((t) -> t.getName());
        return mono;
    }

    public Mono<String> getUserHashMap(final Long id){
        final Mono<User> user = userRepository.getUserForId(id);
        // todo 玩法1
        final Mono<String> mono1 = user.map(user1 -> {
            final JSONObject json = new JSONObject(10);
            json.put("id",user1.getId());
            json.put("name",user1.getName());
            return json.toJSONString();
        });
        // todo 玩法2
        final Mono<String> mono2 = user.map(user1 -> {
            return JSON.toJSONString(user1);
        });
        // todo 玩法３
        final Mono<String> mono = user.map(JSON::toJSONString);
        return mono;
    }

    public Mono<String> addUser(final String name){
        final Mono<Integer> addUser = userRepository.addUser(name);
        return addUser.map(row->{
            return ToolClient.executeRows(row);
        });
    }

    public Mono<String> jsonVoid(final String name){
        final Mono<Integer> addUser = userRepository.addUser(name);
        return addUser.map(row->{
            return ToolClient.executeRows(row);
        });
    }

    public Flux<String> list(final List<String> list){
        final ArrayList<Long> ids = new ArrayList<>(list.size());
        list.forEach(v->{
            ids.add(Long.parseLong(v));
        });
        final Flux<User> listUser = Flux.fromIterable(ids).flatMap(id -> userRepository.findById(id));

        Flux.fromIterable(ids).flatMap(id -> {
            final Mono<User> user = userRepository.findById(id);
            final
            Mono<String> map = user.map(user1 -> {
                return JSON.toJSONString(user1);
            });
            return map;
        });

       /* Flux.fromIterable(ids).flatMap(id ->
            JSONObject.toJSONString(userRepository.findById(id));
        );*/
        /*Flux<String> objectFlux = listUser.flatMap(user -> {
            if(user == null){
                return ToolClient.queryEmpty();
            }else{
                return ToolClient.queryEmpty();
            }
        });*/
        //Flux.fromIterable(userRepository.findAll().).filterWhen();


        Flux<String> map = userRepository.findAll().map(user -> {
            final JSONObject json = new JSONObject(2);
            json.put("id",user.getId());
            json.put("name",user.getName());
            return json.toJSONString();
        });
        return map;

        /*final JSONObject json = new JSONObject(2);
        json.put("code",200);
        json.put("msg",list);
        return Mono.justOrEmpty(json.toJSONString());*/
    }
}