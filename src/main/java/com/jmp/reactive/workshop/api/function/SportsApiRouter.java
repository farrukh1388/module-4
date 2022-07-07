package com.jmp.reactive.workshop.api.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SportsApiRouter {

    @Bean
    public RouterFunction<ServerResponse> sportsRouterFunction(SportsApiHandler sportsApiHandler) {
        return nest(path("/api/v2/sports"), route(GET("/{id}"), sportsApiHandler::getById))
                .andNest(path("/api/v2/sports/name"), route(GET("/{name}"), sportsApiHandler::getByName))
                .andNest(path("/api/v2/sports/name"), route(POST("/{name}"), sportsApiHandler::createByName));
    }
}
