package com.lophiester.webService.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerType {

    PESSOAFISICA(1, "Pessoa fisisica"),
    PESSOAJURIDICA(2, "Pessoa jurídica");

    private int cod;
    private String Description;

    public static CustomerType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (CustomerType x : CustomerType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }

}
