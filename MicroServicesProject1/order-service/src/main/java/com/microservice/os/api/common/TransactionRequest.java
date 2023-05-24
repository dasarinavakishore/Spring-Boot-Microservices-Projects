package com.microservice.os.api.common;

import com.microservice.os.api.entity.Order;

public class TransactionRequest {

	private Order order;
	private Payment payment;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
