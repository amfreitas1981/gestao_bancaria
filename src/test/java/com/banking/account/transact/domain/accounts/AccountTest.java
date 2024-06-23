package com.banking.account.transact.domain.accounts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.banking.account.transact.util.AccountCreator.createAccountToBeSaved;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountTest {

    @Test
    @DisplayName("Teste para mapear retorno sucesso")
    void testAccountSuccess(){
        Account account = createAccountToBeSaved();

        assertNotNull(account);
    }
}
