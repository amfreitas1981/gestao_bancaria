package com.banking.account.transact.domain.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.banking.account.transact.util.DataDetailingTransactionCreator.createDataDetailingTransactionToBeSaved;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataDetailingTransactionTest {

    @Test
    @DisplayName("Teste para mapear retorno sucesso")
    void testDataDetailingTransactionSuccess() {
        DataDetailingTransaction dataDetailingTransaction = createDataDetailingTransactionToBeSaved();

        assertNotNull(dataDetailingTransaction);
    }
}