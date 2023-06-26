package com.lophiester.webService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lophiester.webService.entities.enums.CustomerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    @Email
    private String email;
    private String CpfOrCnpj;
    private Integer type;


    @Setter(AccessLevel.PROTECTED)
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  Set<Order> orders = new HashSet<>();

    @JsonManagedReference
    @Setter(AccessLevel.PROTECTED)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  Set<Address> addresses = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "tb_phone_number")
    private Set<String> phoneNumber = new HashSet<>();

    public User(Long id, String username, String email,  String cpfOrCnpj, CustomerType type) {
        this.id = id;
        this.username = username;
        this.email = email;

        CpfOrCnpj = cpfOrCnpj;
        this.type = type.getCod();
    }

    public CustomerType getType() {
        return CustomerType.toEnum(type);
    }

    public void setType(CustomerType type) {
        this.type = type.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}