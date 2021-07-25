package com.fwtai.reactive.service;

import com.fwtai.reactive.domain.User;
import com.fwtai.reactive.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Transactional(readOnly = true, transactionManager = "readTransactionManager")
@Service
public class ApiService{

    @Autowired
    private ApiRepository apiRepository;

    public Flux<User> findByApp(final String app,final Pageable page){
        return apiRepository.findByApp(app,page);
    }

    public Mono<Long> count(final String app){
        return apiRepository.countByApp(app);
    }
}