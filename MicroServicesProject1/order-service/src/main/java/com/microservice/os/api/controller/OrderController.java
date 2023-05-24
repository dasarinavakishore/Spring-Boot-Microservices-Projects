package com.microservice.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.os.api.common.TransactionRequest;
import com.microservice.os.api.common.TransactionResponse;
import com.microservice.os.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/bookorder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {

		return orderService.saveOrder(request);
	}

}
