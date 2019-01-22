package com.fankun.webflux;

import com.fankun.repository.UserRepository;
import com.fankun.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler){
        System.out.println("web flux =============================");
        return RouterFunctions.route(
                RequestPredicates.POST("/webflux/user/save"),
                userHandler::save);
    }

    @Bean
    @Autowired  //这个@Autowired可以不用
    public RouterFunction<ServerResponse> routerFunctionUsers(UserRepository userRepository){
        System.out.println("web flux =============================");
        Collection<User> users = userRepository.findAll();
        Flux<User> userFlux = Flux.fromIterable(users);
        Mono<Collection<User>> mono = Mono.just(users);//两个是等价的
        //下面不能等价mono,Collection<User>.class不存在
        return RouterFunctions.route(
                RequestPredicates.path("/users"),//get、post都支持
                request->ServerResponse.ok().body(userFlux,User.class));
    }
}
