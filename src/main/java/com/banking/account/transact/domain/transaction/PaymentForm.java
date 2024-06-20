package com.banking.account.transact.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentForm implements ComputeTransactionRepository {
    /*
    Use as seguintes siglas para as formas de pagamento:
    P => Pix -> Não haverá custo na transação
    C => Cartão de Crédito -> Deverá calcular 5% sobre o valor informado
    D => Cartão de Débito -> Deverá calcular 3% sobre o valor informado
    */

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
