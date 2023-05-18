package com.lophiester.webService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lophiester.webService.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    Instant date;

    private Integer orderStatus;

    public Order(Long id,Instant date, OrderStatus orderStatus) {
        this.id = id;
        this.date = date;
        setOrderStatus(orderStatus);
    }

    public OrderStatus getOrderStatus() {

        return OrderStatus.fromValue(orderStatus);}


    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
        this.orderStatus = orderStatus.getValue();
    }}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}