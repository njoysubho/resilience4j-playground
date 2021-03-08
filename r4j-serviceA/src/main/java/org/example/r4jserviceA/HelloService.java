package org.example.r4jserviceA;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.example.r4jserviceA.clients.ServiceBClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class HelloService{
    private final ServiceBClient serviceBClient;
    @TimeLimiter(name = "serviceB#getReply")
    @Bulkhead(name = "serviceB#getReply", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> getReply(){
        return CompletableFuture.completedFuture(serviceBClient.getReply().getBody());
    }

    @CircuitBreaker(name = "serviceB#circuitbreaker")
    public String circuitBreakingResponse(){
        return serviceBClient.circuitBreakingResponse().getBody();
    }

  @Bulkhead(name = "serviceB#semaphore",type = Bulkhead.Type.SEMAPHORE)
  public String semaphoreBulkheadResponse(){
    return serviceBClient.semaphoreBulkheadResponse().getBody();
  }

  @RateLimiter(name = "serviceB#ratelimiter")
  public String rateLimiter(){
    return serviceBClient.rateLimiter().getBody();
  }

    @Retry(name = "serviceB#retry")
    public String retry(){
        return serviceBClient.retry().getBody();
    }

    public String defaultResponse(Throwable th){
        return "Circuit is open default response";
    }
}
