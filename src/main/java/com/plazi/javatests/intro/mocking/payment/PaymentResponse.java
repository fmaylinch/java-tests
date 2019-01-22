package com.plazi.javatests.intro.mocking.payment;

public class PaymentResponse {

    private final ResponseCode code;

    public PaymentResponse(ResponseCode code) {
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }
}
