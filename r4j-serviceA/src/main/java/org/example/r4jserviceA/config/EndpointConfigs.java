package org.example.r4jserviceA.config;

import org.example.r4jserviceA.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class EndpointConfigs{
    @Autowired
    private HelloService helloService;

    @Bean
    public RouterFunction<ServerResponse> sayHello(){
        return RouterFunctions.route()
                .GET("/v1/reply", serverRequest -> ServerResponse.ok().body(helloService.getReply()))
                .GET("/v1/circuit-breaker", serverRequest -> ServerResponse.ok().body(helloService.circuitBreakingResponse()))
                .GET("/v1/semaphore-bulkhead", serverRequest -> ServerResponse.ok().body(helloService.semaphoreBulkheadResponse()))
                .GET("/v1/rate-limiter", serverRequest -> ServerResponse.ok().body(helloService.rateLimiter()))
                .GET("/v1/retry", serverRequest -> ServerResponse.ok().body(helloService.retry()))
                .build();
    }
}
