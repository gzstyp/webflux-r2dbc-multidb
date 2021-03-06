package com.fwtai.reactive.controller;

import com.fwtai.reactive.domain.Product;
import com.fwtai.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * todo 整个 reactive 就是class实现Publisher,对外发布信息,而Subscriber接收信息;
 *  Flux<T> 或 Mono<T> 均实现 implements CorePublisher<T>,而接口CorePublisher有继承Publisher;
 *  Publisher 仅有一个 subscribe(Subscriber<? super T> s); Subscriber就是订阅者,接收者
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //http://127.0.0.1:8802/products/1
    @GetMapping("/products/{id}")
    public Mono<Product> product(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    //http://127.0.0.1:8802/products/byIds?ids=1,2,3
    @GetMapping("/products/byIds")//todo Flux或Mono 是Publisher,是发布者;而接收者是 Subscriber,是Subscriber;一个是发布者,一个是订阅接收者接收;这是这样通讯的;主要实现Publisher的class就能对外发布或提供信息
    public Flux<Product> product(@RequestParam List<Long> ids) {
        return productService.getProducts(ids);
    }

    //http://127.0.0.1:8802/products/saveProducts/car
    @GetMapping(value = "/products/saveProducts/{name}")
    public Mono<Product> saveProducts(@PathVariable String name) {
        return productService.saveProducts(name);
    }
}