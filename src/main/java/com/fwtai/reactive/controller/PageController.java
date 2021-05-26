package com.fwtai.reactive.controller;

import com.fwtai.reactive.tool.ToolClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * todo 整个 reactive 就是class实现Publisher,对外发布信息,而Subscriber接收信息;
 *  Flux<T> 或 Mono<T> 均实现 implements CorePublisher<T>,而接口CorePublisher有继承Publisher;
 *  Publisher 仅有一个 subscribe(Subscriber<? super T> s); Subscriber就是订阅者,接收者
*/
@RestController
public class PageController{

    @GetMapping("/")
    public Mono<String> product(){
        return ToolClient.responseJson("欢迎访问");
    }
}