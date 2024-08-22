package com.banking.account.transact.domain.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class DataRegistrationTransactionTest {

    @Test
    public void testConstructorAndAccessors() {
        // Dados de teste
        PaymentForm paymentForm = PaymentForm.C;
        BigDecimal amount = new BigDecimal("100.00");
        String accountNumber = "123456";

        // Instancia o record
        DataRegistrationTransaction transaction = new DataRegistrationTransaction(paymentForm, amount, accountNumber);

        // Verifica se os valores estão corretos
        assertEquals(paymentForm, transaction.formaPagamento());
        assertEquals(amount, transaction.valor());
        assertEquals(accountNumber, transaction.numeroConta());
    }

    @Test
    public void testCopyConstructor() {
        // Dados de teste
        PaymentForm paymentForm = PaymentForm.D;
        BigDecimal amount = new BigDecimal("50.00");
        String accountNumber = "654321";

        // Instancia o record
        DataRegistrationTransaction original = new DataRegistrationTransaction(paymentForm, amount, accountNumber);

        // Instancia o record usando o construtor de cópia
        DataRegistrationTransaction copy = new DataRegistrationTransaction(original);

        // Verifica se o construtor de cópia funciona corretamente
        assertEquals(original.formaPagamento(), copy.formaPagamento());
        assertEquals(original.valor(), copy.valor());
        assertEquals(original.numeroConta(), copy.numeroConta());
    }
}

