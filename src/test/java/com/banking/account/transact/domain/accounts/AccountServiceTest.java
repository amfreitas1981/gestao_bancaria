package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder()
                .id(1L)
                .numeroConta("123456")
                .saldo(BigDecimal.valueOf(1000))
                .ativo(true)
                .build();
    }

    @Test
    void shouldSaveNewAccountSuccessfully() {
        when(accountRepository.findAccountByAtivoTrue(anyString())).thenReturn(null);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.saveAccount(account);

        assertNotNull(savedAccount);
        assertEquals("123456", savedAccount.getNumeroConta());
        verify(accountRepository, times(1)).findAccountByAtivoTrue(anyString());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void shouldThrowExceptionWhenAccountAlreadyExists() {
        Account existingAccount = Account.builder()
                .id(2L)
                .numeroConta("123456")
                .saldo(BigDecimal.valueOf(2000))
                .ativo(true)
                .build();

        when(accountRepository.findAccountByAtivoTrue(anyString())).thenReturn(existingAccount);

        ValidationException exception = assertThrows(ValidationException.class, () -> accountService.saveAccount(account));

        assertEquals("Conta já cadastrada!", exception.getMessage());
        verify(accountRepository, times(1)).findAccountByAtivoTrue(anyString());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void shouldFindActiveAccountByAccountNumber() {
        when(accountRepository.findAccountByAtivoTrue(anyString())).thenReturn(account);

        Optional<Account> foundAccount = accountService.findAccount("123456");

        assertTrue(foundAccount.isPresent());
        assertEquals("123456", foundAccount.get().getNumeroConta());
        verify(accountRepository, times(1)).findAccountByAtivoTrue(anyString());
    }

    @Test
    void shouldNotFindInactiveAccountByAccountNumber() {
        when(accountRepository.findAccountByAtivoTrue(anyString())).thenReturn(null);

        Optional<Account> foundAccount = accountService.findAccount("123456");

        assertFalse(foundAccount.isPresent());
        verify(accountRepository, times(1)).findAccountByAtivoTrue(anyString());
    }
}
