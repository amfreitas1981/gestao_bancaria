package com.banking.account.transact.domain.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PaymentFormTest {

    @Test
    void testComputeTaxTransaction_P() {
        PaymentForm paymentForm = PaymentForm.P;
        BigDecimal valor = BigDecimal.valueOf(100.00);
        BigDecimal result = paymentForm.computeTaxTransaction(valor);
        assertEquals(valor, result, "O valor retornado para o tipo P deve ser o mesmo que o valor de entrada.");
    }

    @Test
    void testComputeTaxTransaction_C() {
        PaymentForm paymentForm = PaymentForm.C;
        BigDecimal valor = BigDecimal.valueOf(100.00);
        BigDecimal expected = valor.multiply(BigDecimal.valueOf(0.05)).add(valor);
        BigDecimal result = paymentForm.computeTaxTransaction(valor);
        assertEquals(expected, result, "O valor retornado para o tipo C deve ser o valor de entrada mais 5% de imposto.");
    }

    @Test
    void testComputeTaxTransaction_D() {
        PaymentForm paymentForm = PaymentForm.D;
        BigDecimal valor = BigDecimal.valueOf(100.00);
        BigDecimal expected = valor.multiply(BigDecimal.valueOf(0.03)).add(valor);
        BigDecimal result = paymentForm.computeTaxTransaction(valor);
        assertEquals(expected, result, "O valor retornado para o tipo D deve ser o valor de entrada mais 3% de imposto.");
    }

    @Test
    void testFromString_ValidValues() {
        assertEquals(PaymentForm.P, PaymentForm.fromString("P"));
        assertEquals(PaymentForm.C, PaymentForm.fromString("C"));
        assertEquals(PaymentForm.D, PaymentForm.fromString("D"));
    }

    @Test
    void testFromString_InvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PaymentForm.fromString("X");
        });
        assertEquals("PaymentForm inválido: X", exception.getMessage());
    }
}
