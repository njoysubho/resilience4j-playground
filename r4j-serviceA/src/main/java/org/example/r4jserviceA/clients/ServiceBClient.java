package org.example.r4jserviceA.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serviceB",url = "http://localhost:8081")
public interface ServiceBClient{
    @GetMapping("/v1/reply")
    ResponseEntity<String> getReply();

    @GetMapping("/v1/circuit-breaker")
    ResponseEntity<String> circuitBreakingResponse();

    @GetMapping("/v1/semaphore-bulkhead")
    ResponseEntity<String> semaphoreBulkheadResponse();

    @GetMapping("/v1/rate-limiter")
    ResponseEntity<String> rateLimiter();

    @GetMapping("/v1/retry")
    ResponseEntity<String> retry();
}
