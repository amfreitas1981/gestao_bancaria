package com.banking.account.transact.domain.accounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void testAccountInitialization() {
        DataRegistrationAccount dataRegistrationAccount = new DataRegistrationAccount("123456", new BigDecimal("1000.00"));
        account = new Account(dataRegistrationAccount);

        assertEquals("123456", account.getNumeroConta());
        assertEquals(new BigDecimal("1000.00"), account.getSaldo());
        assertNull(account.getId());
    }

    @Test
    void testAccountInitializationFromDetailingAccount() {
        DataDetailingAccount dataDetailingAccount = new DataDetailingAccount(1L, "123456", new BigDecimal("1000.00"));
        account = new Account(dataDetailingAccount);

        assertEquals("123456", account.getNumeroConta());
        assertEquals(new BigDecimal("1000.00"), account.getSaldo());
        assertNull(account.getId());
    }

    @Test
    void testDeleteOrInvalidateInformations() {
        account.setAtivo(true);
        account.deleteOrInvalidateInformations();

        assertFalse(account.getAtivo());
    }

    @Test
    public void testAccountEqualsAndHashCode() {
        Long id = 1L;
        String numeroConta = "123456";
        BigDecimal saldo = BigDecimal.valueOf(200);
        Boolean ativo = true;

        Account account1 = new Account(id, numeroConta, saldo, ativo);
        Account account2 = new Account(id, numeroConta, saldo, ativo);

        assertEquals(account1, account2);
        assertEquals(account1.hashCode(), account2.hashCode());
    }
}
