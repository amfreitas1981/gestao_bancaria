package com.banking.account.transact.domain.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.banking.account.transact.util.DataRegistrationTransactionCreator.createDataRegistrationTransactionToBeSaved;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataRegistrationTransactionTest {

    @Test
    @DisplayName("Teste para mapear retorno sucesso")
    void testDataRegistrationTransactionSuccess(){
        DataRegistrationTransaction dataRegistrationTransaction = createDataRegistrationTransactionToBeSaved();

        assertNotNull(dataRegistrationTransaction);
    }
}
