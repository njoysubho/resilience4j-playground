package org.example.r4jserviceB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Random;

@Configuration(proxyBeanMethods = false)
public class GreetEndpoint{
    private final Random random = new Random();

    @Bean
    public RouterFunction<ServerResponse> greet(){
        return RouterFunctions.route()
                .GET("/v1/reply", (req) -> slowResponse())
                .GET("/v1/circuit-breaker", request -> circuitBreakingResponse())
                .GET("/v1/semaphore-bulkhead", request -> ServerResponse.ok().body("semaphore-bulkhead"))
                .GET("/v1/rate-limiter", request -> ServerResponse.ok().body("rate-limited"))
                .GET("/v1/retry", request -> retryResponse())

                .build();
    }

    private ServerResponse retryResponse(){
        var dice = random.nextFloat();
        if (dice > 0.5) {
            throw new RuntimeException("Error");
        }
        return ServerResponse.ok().body("retry");
    }

    private ServerResponse circuitBreakingResponse(){
        throw new IllegalArgumentException("Not implemented");
    }

    public ServerResponse slowResponse() throws InterruptedException{
        Thread.sleep(2 * 1000);
        return ServerResponse.ok().body("Hello");
    }
}
