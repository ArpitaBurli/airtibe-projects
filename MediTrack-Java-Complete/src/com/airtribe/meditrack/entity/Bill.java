package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.util.Validator;

public class Bill implements Payable {
    private final String billId;
    private final String patientId;
    private final double baseAmount;
    private final double tax;
    private final double total;
    private boolean paid;

    public Bill(String billId, String patientId, double baseAmount, double tax) {
        Validator.requireNonBlank(billId, "billId");
        Validator.requireNonBlank(patientId, "patientId");
        Validator.requireNonNegative(baseAmount, "baseAmount");
        Validator.requireNonNegative(tax, "tax");
        this.billId = billId;
        this.patientId = patientId;
        this.baseAmount = baseAmount;
        this.tax = tax;
        this.total = baseAmount + tax;
    }

    public String getBillId() { return billId; }
    public String getPatientId() { return patientId; }
    public double getBaseAmount() { return baseAmount; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
    public boolean isPaid() { return paid; }

    public void markPaid() {
        this.paid = true;
    }

    @Override
    public double calculateAmount() {
        return total;
    }

    @Override
    public String toString() {
        return "Bill{id='" + billId + "', patientId='" + patientId
                + "', base=" + baseAmount + ", tax=" + tax
                + ", total=" + total + ", paid=" + paid + "}";
    }
}
