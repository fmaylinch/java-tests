package com.plazi.javatests.intro.mocking.payment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.BDDMockito.*;

public class PaymentProcessorShould {

    private PaymentGateway paymentGateway;
    private PaymentProcessor paymentProcessor;

    @Before
    public void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        paymentProcessor = new PaymentProcessor(paymentGateway);
    }

    @Test
    public void use_payment_gateway() {

        given(paymentGateway.performPayment(any()))
                .willReturn(new PaymentResponse(ResponseCode.OK));

        paymentProcessor.performPayment("xyz", 10);

        verify(paymentGateway, times(1))
                .performPayment(new PaymentRequest("xyz", 10));
    }

    @Test(expected = PaymentException.class)
    public void throw_exception_if_not_enough_funds() {

        given(paymentGateway.performPayment(any()))
                .willReturn(new PaymentResponse(ResponseCode.NOT_ENOUGH_FUNDS));

        paymentProcessor.performPayment("xyz", 10);
    }

    @Test
    public void retry_if_network_error() {

        final AtomicInteger invocations = new AtomicInteger(0);

        // Simulate network error until invocation 3
        given(paymentGateway.performPayment(any()))
                .will((Answer<PaymentResponse>) invocation -> {
                    int i = invocations.incrementAndGet();
                    return i >= 3 ?
                            new PaymentResponse(ResponseCode.OK)
                            : new PaymentResponse(ResponseCode.NETWORK_ERROR);
                });

        paymentProcessor.performPayment("xyz", 10);

        verify(paymentGateway, times(3))
                .performPayment(new PaymentRequest("xyz", 10));
    }
}