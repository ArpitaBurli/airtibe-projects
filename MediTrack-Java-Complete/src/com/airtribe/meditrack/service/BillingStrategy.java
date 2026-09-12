package com.airtribe.meditrack.service;

public interface BillingStrategy {
    double calculateTax(double baseAmount);
}
