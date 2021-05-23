package com.fwtai.reactive.config;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建Flux的多种方式
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/5/23 22:06
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
public final class FluxDemo{

    protected static void createFluxFromData(){
        final Flux<Integer> justInteger = Flux.just(1,2,3,4,5,6);
        final Flux<String> justString = Flux.just("tom","fwtai","yinlz");
        final Flux<Integer> integerFlux = Flux.fromArray(new Integer[]{1,2,3,4,5,6});
        final Flux<Integer> fromIterable = Flux.fromIterable(Arrays.asList(1,2,3,4,5,6));
        final Flux<Integer> fromStream = Flux.fromStream(Stream.of(1,2,3,4,5,6));
        final Flux<Integer> range = Flux.range(1,6);
    }
}