package com.lophiester.webService.entities;

import com.lophiester.webService.entities.enums.StatusPayment;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class PaymentWithCard extends Payment {
    @Getter
    @Setter
    private Integer numberOfInstallments;

    public PaymentWithCard(Long id, StatusPayment status, Order order, Integer numberOfInstallments) {
        super(id, status, order);
        this.numberOfInstallments = numberOfInstallments;
    }
}
