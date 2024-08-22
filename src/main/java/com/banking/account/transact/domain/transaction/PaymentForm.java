package com.banking.account.transact.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public enum PaymentForm implements ComputeTransactionRepository {
    P {
        @Override
        public BigDecimal computeTaxTransaction(BigDecimal valor){
            return valor;
        }
    },
    C {
        @Override
        public BigDecimal computeTaxTransaction(BigDecimal valor){
            return valor.multiply(BigDecimal.valueOf(0.05)).add(valor);
        }
    },
    D {
        @Override
        public BigDecimal computeTaxTransaction(BigDecimal valor){
            return valor.multiply(BigDecimal.valueOf(0.03)).add(valor) ;
        }
    };

    @JsonCreator
    public static PaymentForm fromString(String value) {
        for (PaymentForm paymentForm : PaymentForm.values()) {
            if (paymentForm.name().equalsIgnoreCase(value)) {
                return paymentForm;
            }
        }
        throw new IllegalArgumentException("PaymentForm inválido: " + value);
    }
}
