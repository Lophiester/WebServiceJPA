package com.lophiester.webService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lophiester.webService.entities.enums.StatusPayment;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Entity
public class PaymentWithBoleto extends Payment {
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dueDate;
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant paymentDate;

    public PaymentWithBoleto(Long id, StatusPayment status, Order order, Instant dueDate, Instant paymentDate) {
        super(id, status, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
