package com.p4pijk.carrental.model.receipt;

public enum OrderStatus {
    PENDING("Pending"),
    REJECTED("Rejected"),
    PAID("Paid"),
    RETURNED("Returned");

    OrderStatus(String s) {

    }
}
