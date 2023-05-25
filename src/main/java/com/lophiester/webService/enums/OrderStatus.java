package com.lophiester.webService.enums;

import jakarta.persistence.Enumerated;


public enum OrderStatus {

    PAYMENT_PENDING(1),
    PAYMENT_SUCCESS(2),
    PAYMENT_FAILED(3),
    SHIPPED(4),
    DELIVERED(5);

    @Enumerated
    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant " + OrderStatus.class.getName() + "." + value);
    }
}