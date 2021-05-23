package com.fwtai.reactive.controller;

import com.fwtai.reactive.domain.User;
import com.fwtai.reactive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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

    //http://127.0.0.1:8802/user/getUser/1
    @GetMapping("/getUser/{id}")
    public Mono<String> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //http://127.0.0.1:8802/user/getUserHashMap/1
    @GetMapping("/getUserHashMap/{id}")
    public Mono<String> getUserHashMap(@PathVariable Long id) {
        return userService.getUserHashMap(id);
    }

    //http://127.0.0.1:8802/user/jsonVoid?name=typ512
    @GetMapping("/jsonVoid")
    public Mono<String> jsonVoid(final ServerHttpRequest request,final ServerHttpResponse response){
        response.getHeaders().add("Content-Type","text/html;charset=utf-8");
        final String name = request.getQueryParams().get("name").get(0);
        return userService.jsonVoid(name);
    }

    //http://127.0.0.1:8802/user/editUser/whj/4
    @GetMapping(value = "/editUser/{name}/{id}",produces = MediaType.TEXT_HTML_VALUE)//todo 解决IE8请求时出现下载的bug
    public Mono<String> editUser(@PathVariable String name,@PathVariable Long id) {
        return userService.editUser(name,id);
    }

    //http://127.0.0.1:8802/user/addUser?name=typ
    @GetMapping(value = "/addUser",produces = MediaType.TEXT_HTML_VALUE)//todo 解决IE8请求时出现下载的bug
    public Mono<String> addUser(final ServerHttpRequest request,final ServerHttpResponse response){
        final String name = request.getQueryParams().get("name").get(0);
        return userService.addUser(name);
    }

    //http://127.0.0.1:8802/user/list?ids=1,2,3
    @GetMapping(value = "/list",produces = MediaType.TEXT_HTML_VALUE)//todo 解决IE8请求时出现下载的bug
    public Mono<String> list(final ServerHttpRequest request,final ServerHttpResponse response){
        final String ids = request.getQueryParams().get("ids").get(0);
        return userService.list(Arrays.asList(ids.split(",")));
    }
}