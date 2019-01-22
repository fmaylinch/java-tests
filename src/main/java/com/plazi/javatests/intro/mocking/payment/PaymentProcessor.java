package com.plazi.javatests.intro.mocking.payment;

public class PaymentProcessor {

    private final PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void performPayment(String accountId, double amount) {

        PaymentResponse response = null;

        while (response == null || response.getCode() == ResponseCode.NETWORK_ERROR) {
            response = paymentGateway.performPayment(new PaymentRequest(accountId, amount));
        }

        if (response.getCode() == ResponseCode.NOT_ENOUGH_FUNDS) {
            throw new PaymentException();
        }
    }
}
