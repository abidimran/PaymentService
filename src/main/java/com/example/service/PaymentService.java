package com.example.service;

import java.util.List;

import com.example.model.Payment;

public interface PaymentService {
	
	
	public List<Payment> getAllPaymentDetails();
	public Payment getPaymentDetails(Integer paymentId);
	public Payment savePayment(Payment payment);
	public Payment updatePayment(Payment payment);
	public void deletePayment(Integer paymentId);
	
	

}
