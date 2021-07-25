package com.fwtai.reactive.repository;

import com.fwtai.reactive.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiRepository extends ReactiveCrudRepository<User,Long>{

    @Query("select id,name from product limit 5")
    Flux<User> findByApp(final String app,final Pageable page);

    @Query("SELECT COUNT(1) total FROM product")
    Mono<Long> countByApp(final String app);
}