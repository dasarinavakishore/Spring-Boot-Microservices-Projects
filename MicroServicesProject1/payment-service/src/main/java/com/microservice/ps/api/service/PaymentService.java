package com.microservice.ps.api.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ps.api.entity.Payment;
import com.microservice.ps.api.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) {
		// random transactionId auto generated
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentStatus(paymentProcessing());
		return paymentRepository.save(payment);
	}

	public String paymentProcessing() {
		// api should be thirdPary payment gateway (paytm/razopay..etc)
		return new Random().nextBoolean() ? "success" : "failure";
	}

	public Payment findPaymentHistoryByOrderId(int orderId) {
		return paymentRepository.findByOrderId(orderId);
	}

}
