package com.fwtai.webflux.repository;

import com.fwtai.webflux.domain.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface userRepository extends ReactiveCrudRepository<User,Long> {

    @Query("SELECT id,name FROM product WHERE id = :id")
    Mono<User> getUserForId(final Long id);

    @Modifying
    @Query("UPDATE product SET name = :name where id = :id")//todo 不支持 #{xxx} 会报错 Binding parameters is not supported for simple statement
    Mono<Integer> editUser(final String name,final Long id);
}