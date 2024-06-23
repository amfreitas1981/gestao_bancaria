package com.banking.account.transact.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentForm implements ComputeTransactionRepository {
    P {
        @Override
        public double computeTaxTransaction(double valor){
            return valor;
        }
    },
    C {
        @Override
        public double computeTaxTransaction(double valor){
            return valor * 0.05 + valor;
        }
    },
    D {
        @Override
        public double computeTaxTransaction(double valor){
            return valor * 0.03 + valor;
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
