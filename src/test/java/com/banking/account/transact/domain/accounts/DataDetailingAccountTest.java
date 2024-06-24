package com.banking.account.transact.domain.accounts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.banking.account.transact.util.DataDetailingAccountCreator.createDataDetailingAccountToBeSaved;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataDetailingAccountTest {

    @Test
    @DisplayName("Teste para mapear retorno sucesso")
    void testDataDetailingAccountSuccess() {
        DataDetailingAccount dataDetailingAccount = createDataDetailingAccountToBeSaved();

        assertNotNull(dataDetailingAccount);
    }
}