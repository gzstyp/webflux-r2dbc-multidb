package com.fwtai.webflux.service;

import com.fwtai.webflux.domain.User;
import com.fwtai.webflux.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional(readOnly = true, transactionManager = "readTransactionManager")
@Service
public class UserService{

    @Autowired
    private userRepository userRepository;

    @Transactional(transactionManager = "writeTransactionManager")
    public Mono<Integer> editUser(final String name,final Long id) {
        return userRepository.editUser(name,id);
    }

    public Mono<User> getUserForId(final Long id){
        return userRepository.getUserForId(id);
    }
}