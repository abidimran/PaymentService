package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Payment;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {


	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping("/ping")
	public ResponseEntity<String> Ping() {

		System.out.println("Coming for Payment Ping");
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

	@GetMapping("/all-payments")
	public ResponseEntity<?> getAllPaymentDetails(){

		List<Payment> payments = paymentService.getAllPaymentDetails();
		if(payments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("No Payment Details Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

	@GetMapping("/payment/{paymentId}")
	public ResponseEntity<?> getPayment(@PathVariable("paymentId") Integer paymentId){
		try {
			Payment payment =paymentService.getPaymentDetails(paymentId);
			return ResponseEntity.status(HttpStatus.OK).body(payment);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("No Payment Detail Found");
		}
	}

	@PostMapping("/payment")
	public ResponseEntity<List<Payment>> creatingPayment(@RequestBody Payment payment){
		System.out.println("PaymentStatus: "+payment.getPaymentStatus()+
				" PaymentDate: "+payment.getPaymentDate());
		paymentService.savePayment(payment);
		List<Payment> payments = paymentService.getAllPaymentDetails();
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

	@DeleteMapping("/payment/{paymentId}")
	public ResponseEntity<String> deletePayment(@PathVariable("paymentId") Integer paymentId){
		try {
			paymentService.deletePayment(paymentId);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("No Payment Detail Found");
		}
	}

	@PutMapping("/payment")
	public ResponseEntity<?> updatePayment(@RequestBody Payment payment){
		try {
			paymentService.updatePayment(payment);
			payment =paymentService.getPaymentDetails(payment.getPaymentId());
			return ResponseEntity.status(HttpStatus.OK).body(payment);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("No Payment Detail Found");
		}

	}


}
