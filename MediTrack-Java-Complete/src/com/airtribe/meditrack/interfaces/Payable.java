package com.airtribe.meditrack.interfaces;

public interface Payable {
    double calculateAmount();

    default boolean isPaymentRequired() {
        return calculateAmount() > 0;
    }
}
