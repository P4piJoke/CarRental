package com.p4pijk.carrental.model.receipt;

public enum OrderStatus {

    PENDING("Pending"),
    REJECTED("Rejected"),
    RETURNED("Returned"),
    ACTIVE("Active"),
    CLOSED("Closed");

    OrderStatus(String status) {
    }
}
