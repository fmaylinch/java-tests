package com.plazi.javatests.intro.mocking.payment;

public interface PaymentGateway {

    PaymentResponse performPayment(PaymentRequest request);
}
