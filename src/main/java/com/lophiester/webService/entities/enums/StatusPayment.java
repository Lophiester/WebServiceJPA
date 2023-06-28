package com.lophiester.webService.entities.enums;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPayment {
    PAYMENT_PENDING(1, "Pending"),
    PAYMENT_SUCCESS(2, "Success"),
    PAYMENT_FAILED(3, "Failed");

    @Enumerated
    private int cod;
    private String Description;

    public static StatusPayment toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (StatusPayment x : StatusPayment.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }

}

