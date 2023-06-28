package com.lophiester.webService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lophiester.webService.entities.enums.StatusPayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_payment")
public abstract class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment(Long id, StatusPayment status, Order order) {
        this.id = id;
        this.status = status.getCod();
        this.order = order;
    }

    public StatusPayment getStatus() {
        return StatusPayment.toEnum(status);
    }

    public void setStatus(StatusPayment status) {
        this.status = status.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}