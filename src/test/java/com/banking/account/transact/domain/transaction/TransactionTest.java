package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.accounts.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionTest {

    @Test
    public void testTransactionConstructor() {
        // Dados de entrada
        PaymentForm paymentForm = PaymentForm.C;
        BigDecimal valor = BigDecimal.valueOf(100);
        String numeroConta = "123456789";

        // Mockando a conta
        Account account = mock(Account.class);
        when(account.getId()).thenReturn(1L);

        // Criando o objeto Transaction
        Transaction transaction = new Transaction(
                new DataRegistrationTransaction(paymentForm, valor, numeroConta),
                account
        );

        // Verificando os valores
        assertEquals(paymentForm, transaction.getFormaPagamento());
        assertEquals(valor, transaction.getValor());
        assertEquals(account, transaction.getAccount());
    }

    @Test
    public void testPaymentFormComputeTaxTransaction() {
        BigDecimal value = BigDecimal.valueOf(100);

        BigDecimal expectedP = value;
        BigDecimal expectedC = value.multiply(BigDecimal.valueOf(0.05)).add(value);
        BigDecimal expectedD = value.multiply(BigDecimal.valueOf(0.03)).add(value);

        assertEquals(expectedP, PaymentForm.P.computeTaxTransaction(value));
        assertEquals(expectedC, PaymentForm.C.computeTaxTransaction(value));
        assertEquals(expectedD, PaymentForm.D.computeTaxTransaction(value));
    }

    @Test
    public void testTransactionBuilder() {
        // Dados de entrada
        PaymentForm paymentForm = PaymentForm.C;
        BigDecimal valor = BigDecimal.valueOf(100);
        Account account = mock(Account.class);

        // Usando o builder para criar uma instância de Transaction
        Transaction transaction = Transaction.builder()
                .formaPagamento(paymentForm)
                .valor(valor)
                .account(account)
                .build();

        // Verificando os valores
        assertEquals(paymentForm, transaction.getFormaPagamento());
        assertEquals(valor, transaction.getValor());
        assertEquals(account, transaction.getAccount());
    }

    @Test
    public void testTransactionAllArgsConstructor() {
        // Dados de entrada
        Long id = 1L;
        PaymentForm paymentForm = PaymentForm.D;
        BigDecimal valor = BigDecimal.valueOf(150);
        Account account = mock(Account.class);

        // Usando o construtor gerado por @AllArgsConstructor
        Transaction transaction = new Transaction(id, paymentForm, valor, account);

        // Verificando os valores
        assertEquals(id, transaction.getId());
        assertEquals(paymentForm, transaction.getFormaPagamento());
        assertEquals(valor, transaction.getValor());
        assertEquals(account, transaction.getAccount());
    }

    @Test
    public void testTransactionEqualsAndHashCode() {
        // Dados de entrada
        Long id = 1L;
        PaymentForm paymentForm = PaymentForm.P;
        BigDecimal valor = BigDecimal.valueOf(200);
        Account account = mock(Account.class);

        // Criando duas instâncias de Transaction com o mesmo ID
        Transaction transaction1 = new Transaction(id, paymentForm, valor, account);
        Transaction transaction2 = new Transaction(id, paymentForm, valor, account);

        // Verificando se as duas instâncias são consideradas iguais
        assertEquals(transaction1, transaction2);
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }
}
