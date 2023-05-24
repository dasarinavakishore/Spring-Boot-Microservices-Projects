package com.microservice.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/orderfallback")
	public Mono<String> orderServiceFallBack() {
		return Mono.just("Order Service is taking too long to respond or is down. Please try again later.");
	}

	@RequestMapping("/paymentfallback")
	public Mono<String> paymentServiceFallBack() {
		return Mono.just("Payment Service is taking too long to respond or is down. Please try again later.");
	}

}
