package com.banking.account.transact.domain.accounts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Captor
    ArgumentCaptor<Account> argumentCaptor;

    @Test
    @DisplayName("Deve salvar conta com sucesso")
    void saveAccountSuccess() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setNumero_conta("234");
        account.setSaldo(180.37);
        account.setAtivo(true);

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepository.findAccountByAtivoTrue(account.getNumero_conta())).thenReturn(account);

        // Action
        accountService.saveAccount(account);

        // Assertions
        Mockito.verify(accountRepository).findAccountByAtivoTrue(account.getNumero_conta());

        Mockito.verify(accountRepository).save(argumentCaptor.capture());
        Account saveAccount = argumentCaptor.getValue();

        assertThat(saveAccount.getId()).isNotNull();
        assertThat(saveAccount.getNumero_conta()).isNotNull();
    }

    @Test
    @DisplayName("Erro ao tentar salvar conta")
    void saveAccountError() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setNumero_conta(null);
        account.setSaldo(180.37);
        account.setAtivo(true);

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepository.findAccountByAtivoTrue(account.getNumero_conta())).thenReturn(account);

        // Action
        accountService.saveAccount(account);

        // Assertions
        Mockito.verify(accountRepository).findAccountByAtivoTrue(account.getNumero_conta());

        Mockito.verify(accountRepository).save(argumentCaptor.capture());
        Account saveAccount = argumentCaptor.getValue();

        assertThat(saveAccount.getId()).isNotNull();
        assertThat(saveAccount.getNumero_conta()).isNull();
    }
}