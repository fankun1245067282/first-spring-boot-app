package com.fankun.repository;

import com.fankun.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final ConcurrentMap<Long, User> repository = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Boolean save(User user){
        Long id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    //effective java II
    public Collection<User> findAll(){
        return repository.values();
    }


}
