package com.airtribe.meditrack.entity;

import java.util.Objects;

/**
 * Immutable billing summary.
 */
public final class BillSummary {
    private final String billId;
    private final String patientId;
    private final double amount;
    private final String status;

    public BillSummary(String billId, String patientId, double amount, String status) {
        this.billId = Objects.requireNonNull(billId);
        this.patientId = Objects.requireNonNull(patientId);
        this.amount = amount;
        this.status = Objects.requireNonNull(status);
    }

    public String getBillId() { return billId; }
    public String getPatientId() { return patientId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "BillSummary{billId='" + billId + "', patientId='" + patientId
                + "', amount=" + amount + ", status='" + status + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillSummary)) return false;
        BillSummary that = (BillSummary) o;
        return Double.compare(amount, that.amount) == 0
                && billId.equals(that.billId)
                && patientId.equals(that.patientId)
                && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, patientId, amount, status);
    }
}
