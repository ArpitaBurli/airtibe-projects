package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;

public class StandardBillingStrategy implements BillingStrategy {
    @Override
    public double calculateTax(double baseAmount) {
        return baseAmount * Constants.TAX_RATE;
    }
}
