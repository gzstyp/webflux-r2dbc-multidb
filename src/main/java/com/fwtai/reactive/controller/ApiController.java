package com.fwtai.reactive.controller;

import com.fwtai.reactive.domain.User;
import com.fwtai.reactive.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController{

    @Autowired
    private ApiService apiService;

    //http://127.0.0.1:8802/api/listData?pageSize=10&page=1&app=aa
    @GetMapping("/listData")
    public Mono<ResponseEntity> getApplog(@PathVariable String app,final Pageable page,final ServerHttpResponse response){
        response.getHeaders().add("Content-Type","text/html;charset=utf-8");
        final Mono<List<User>> applogs = apiService.findByApp(app,page).collectList();
        final Mono<Long> total = apiService.count(app);
        return applogs.zipWith(total,(log,size) -> ResponseEntity.ok().body(applogs));//todo 待考证
        //return applogs.zipWith(total,(log,size) -> ResponseEntity.ok().headers(PaginationUtil.generatePaginationHttpHeaders(page,log,size,"base")).body(applogs));
    }
}