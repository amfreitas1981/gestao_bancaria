package com.banking.account.transact.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentFormEnum {
    /*
    Use as seguintes siglas para as formas de pagamento:
    P => Pix
    C => Cartão de Crédito
    D => Cartão de Débito
    */

    P,
    C,
    D;

    @JsonCreator
    public static PaymentFormEnum fromString(String value) {
        for (PaymentFormEnum paymentFormEnum : PaymentFormEnum.values()) {
            if (paymentFormEnum.name().equalsIgnoreCase(value)) {
                return paymentFormEnum;
            }
        }
        throw new IllegalArgumentException("PaymentFormEnum inválido: " + value);
    }
}
