package com.fankun.controller;


import com.fankun.repository.UserRepository;
import com.fankun.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TestController {

    private final UserRepository userRepository;

    @Autowired
    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index.do")
    public String index(){
        return "hello,world";
    }

    @PostMapping("/user/save")
    public User save(String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/user/findall")
    public Collection<User> findAll(){
        return userRepository.findAll();
    }
}
