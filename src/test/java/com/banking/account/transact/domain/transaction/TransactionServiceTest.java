package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.AccountNotFoundException;
import com.banking.account.transact.domain.NegativeBalanceException;
import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction_Success() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(100), "12345");
        Account account = new Account(1L, "12345", BigDecimal.valueOf(200), true);

        when(accountRepository.findAccountByAtivoTrue("12345")).thenReturn(account);
        when(transactionRepository.findByAccountNumeroConta("12345")).thenReturn(null);

        transactionService.saveTransaction(data);

        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testSaveTransaction_NullAccountNumber() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(100), null);

        assertThrows(AccountNotFoundException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_AccountNotFound() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(100), "12345");

        when(accountRepository.findAccountByAtivoTrue("12345")).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_InsufficientBalance() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(300), "12345");
        Account account = new Account(1L, "12345", BigDecimal.valueOf(200), true);

        when(accountRepository.findAccountByAtivoTrue("12345")).thenReturn(account);
        when(transactionRepository.findByAccountNumeroConta("12345")).thenReturn(null);

        assertThrows(NegativeBalanceException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_MissingPaymentForm() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(null, BigDecimal.valueOf(100), "12345");

        assertThrows(AccountNotFoundException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_AccountInactive() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(100), "12345");
        Account account = new Account(1L, "12345", BigDecimal.valueOf(200), false);

        when(accountRepository.findAccountByAtivoTrue("12345")).thenReturn(account);

        assertThrows(AccountNotFoundException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_NullTransactionValue() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, null, "12345");

        assertThrows(AccountNotFoundException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testSaveTransaction_InvalidPaymentForm() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.fromString("X"), BigDecimal.valueOf(100), "12345");
            transactionService.saveTransaction(data);
        });
    }

    @Test
    void testSaveTransaction_UnexpectedException() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(PaymentForm.P, BigDecimal.valueOf(100), "12345");

        when(accountRepository.findAccountByAtivoTrue("12345")).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> transactionService.saveTransaction(data));
    }

    @Test
    void testComputeTaxTransaction_MissingPaymentForm() {
        DataRegistrationTransaction data = new DataRegistrationTransaction(null, BigDecimal.valueOf(100), "12345");

        assertThrows(InvocationTargetException.class, () -> {
            // Usamos reflexão para acessar o método privado
            Method method = TransactionService.class.getDeclaredMethod("computeTaxTransaction", DataRegistrationTransaction.class);
            method.setAccessible(true);
            method.invoke(transactionService, data);
        });
    }
}
