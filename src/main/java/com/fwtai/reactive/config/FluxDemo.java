package com.fwtai.reactive.config;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
            .subscribe();//todo 这个方法是执行前面的代码,没有这个方法就不执行,它是发起调用!!!即当调用 subscribe()或 block()都会立刻打开管道或运行这个管道
    }

    public static void main(String[] args){
        createFluxFromData();
        System.out.println("---------------");
        createGenerate();
        monoBlock();
    }

    protected static void createGenerate(){
        final Flux<Object> flux = Flux.generate(() -> 1,(state,sink) -> {//1 表示初始值是 1
            sink.next("message #" + state);//把数字变为字符串
            if(state == 10){
                sink.complete();//如果数字已达到10时就关闭这个通道管道
            }
            return state + 1;//如果数字还没达到把 state + 1
        });
        subscribe("createGenerate,",flux);
    }

    protected static void monoBlock(){
        final Mono<Integer> mono = Mono.just(1);
        mono.doOnSubscribe(subscription -> System.out.println("运行subscription方法"))
            .doOnNext(integer -> System.out.println(integer))
            .block();//todo,它会阻塞,除非保证已调用完;尽量减少使用! 这个方法是执行前面的代码,没有这个方法就不执行,它是发起调用!!!即当调用 block()或 subscribe()都会立刻打开管道或运行这个管道
    }
}