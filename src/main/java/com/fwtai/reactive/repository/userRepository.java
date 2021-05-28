package com.fwtai.reactive.repository;

import com.fwtai.reactive.domain.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public interface userRepository extends ReactiveCrudRepository<User,Long> {

    @Query("SELECT id,name,created_at FROM product WHERE id = :id")
    Mono<User> getUserForId(final Long id);

    @Query("SELECT id,name FROM product WHERE id = :id")
    Mono<HashMap<String,Object>> getUserHashMap(final Long id);//sql 查询时,不能返回list_Map,也不能返回map,但是可以参考 http://www.cocoachina.com/articles/42607

    @Modifying
    @Query("UPDATE product SET name = :name where id = :id")//todo 不支持 #{xxx} 会报错 Binding parameters is not supported for simple statement
    Mono<Integer> editUser(final String name,final Long id);

    @Modifying
    @Query("INSERT INTO product(`name`) VALUES (:name)")
    Mono<Integer> addUser(final String name);

    @Query("select id,name from product limit :section,:pageSize")
    Flux<User> listData(final int section,final int pageSize);

    @Query("SELECT COUNT(1) total FROM product")
    Mono<Integer> listTotal();

    @Query("select id,name from product where name LIKE concat('%',:name,'%') limit :section,:pageSize")
    Flux<User> listData2(final int section,final int pageSize,final String name);

    @Query("SELECT COUNT(1) total FROM product where name LIKE concat('%',:name,'%')")
    Mono<Integer> listTotal2(final String name);
}