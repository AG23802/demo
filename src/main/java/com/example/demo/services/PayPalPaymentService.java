package com.example.demo.services;

import com.example.demo.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("PAYPAL");
        System.out.println("Processing payment of " + 99);
    }
}