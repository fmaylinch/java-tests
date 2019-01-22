package com.plazi.javatests.intro.mocking.payment;

import java.util.Objects;

public class PaymentRequest {

    private final String accountId;
    private final double amount;

    public PaymentRequest(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Double.compare(that.amount, amount) == 0 &&
                accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount);
    }
}
