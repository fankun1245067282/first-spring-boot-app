package com.fankun.webflux;

import com.fankun.domain.User;
import com.fankun.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 返回0-1 ServerResponse
     * 有同步异步方式，当前为同步方式
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> save(ServerRequest serverRequest){
        System.out.printf("[Thread: %s] UserHandler starts saving user...\n",Thread.currentThread().getName());
        //在Spring Web Mvc使用的是@RequstBody
        //在Spring Web Flux使用的是ServerRequest
        //Mono<MyUser>类似于Optional<MyUser>
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        //map相当于转换工作
        Mono<Boolean> booleanMono = userMono.map(userRepository::save);
        return ServerResponse.ok().body(booleanMono,Boolean.class);
    }

}
