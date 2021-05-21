package com.fwtai.webflux.controller;

import com.fwtai.webflux.domain.User;
import com.fwtai.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * todo 整个 reactive 就是class实现Publisher,对外发布信息,而Subscriber接收信息;
 *  Flux<T> 或 Mono<T> 均实现 implements CorePublisher<T>,而接口CorePublisher有继承Publisher;
 *  Publisher 仅有一个 subscribe(Subscriber<? super T> s); Subscriber就是订阅者,接收者
*/
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    //http://127.0.0.1:8802/user/1
    @GetMapping("/{id}")
    public Mono<User> product(@PathVariable Long id) {
        return userService.getUserForId(id);
    }

    //http://127.0.0.1:8802/user/editUser/whj/4
    @GetMapping("/editUser/{name}/{id}")
    public Mono<Integer> editUser(@PathVariable String name,@PathVariable Long id) {
        return userService.editUser(name,id);
    }

    //http://127.0.0.1:8802/user/addUser?name=typ
    @GetMapping(value = "/addUser",produces = MediaType.TEXT_HTML_VALUE)//todo 解决IE8请求时出现下载的bug
    public Mono<String> addUser(final ServerHttpRequest request,final ServerHttpResponse response){
        final String name = request.getQueryParams().get("name").get(0);
        return userService.addUser(name);
    }
}