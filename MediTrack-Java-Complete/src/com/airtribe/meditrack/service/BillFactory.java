package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.util.IdGenerator;

public final class BillFactory {
    private BillFactory() {
    }

    public static Bill createStandardBill(String patientId, double baseAmount,
                                           BillingStrategy strategy) {
        double tax = strategy.calculateTax(baseAmount);
        return new Bill(IdGenerator.getInstance().nextBillId(),
                patientId, baseAmount, tax);
    }
}
