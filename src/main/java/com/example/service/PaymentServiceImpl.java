package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Payment;
import com.example.repository.PaymentRepository;


@Service
public class PaymentServiceImpl implements PaymentService{

	private PaymentRepository paymentRepository;
	
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public List<Payment> getAllPaymentDetails() {
		return paymentRepository.findAll();
	}

	public Payment getPaymentDetails(Integer paymentId) {
		
		return paymentRepository.getById(paymentId);
	}

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public Payment updatePayment(Payment payment) {
		
		Payment paymentUpdate = Payment.builder().paymentStatus(payment.getPaymentStatus()).paymentDate(payment.getPaymentDate()).build();
				
		
		return paymentRepository.save(paymentUpdate);
	}

	public void deletePayment(Integer paymentId) {
		paymentRepository.deleteById(paymentId);
	}

}
