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

        subscribe("justInteger",justInteger);
        subscribe("justString",justString);
        subscribe("integerFlux",integerFlux);
        subscribe("fromIterable",fromIterable);
        subscribe("fromStream",fromStream);
        subscribe("range",range);
    }

    //打开
    protected static void subscribe(final String name,final Flux<?> flux){
        flux.doOnSubscribe(subscription -> System.out.print("name->"+name))
            .doOnNext(c ->System.out.print(c+","))
            .doOnComplete(System.out::println)
            .subscribe();//todo 这个方法是执行前面的代码,没有这个方法就不执行,它是发起调用!!!
    }

    public static void main(String[] args){
        createFluxFromData();
    }
}