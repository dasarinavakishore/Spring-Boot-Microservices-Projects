package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	// testing Retry the API
	// @Retry(name = "default") // it will retry 3 times

	@GetMapping("/sample-api")
//	@Retry(name = "default") // it will retry 3 times
//	@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse") //it will retry as per properties config
//	@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
//	@RateLimiter(name = "default") // eg: 10s => 1000 calss to the sample-api
	@Bulkhead(name = "sample-api") // for concurrent calls
	public String sampleApi() {
		logger.info("Sample API call received");
//		ResponseEntity<String> res = new RestTemplate().getForEntity("http://localhost:8000/some-dummy-url",
//				String.class);
//		return res.getBody();
		return "sample-api";
	}

	public String hardCodedResponse(Exception ex) {
		return "fallback-response";
	}
}
