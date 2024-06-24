package com.banking.account.transact.util;

import com.banking.account.transact.domain.transaction.DataDetailingTransaction;

public class DataDetailingTransactionCreator {

    public static DataDetailingTransaction createDataDetailingTransactionToBeSaved() {
        return new DataDetailingTransaction(
                null,
                "234",
                184.42
        );
    }

    public static DataDetailingTransaction createDataDetailingTransactionValid() {
        return new DataDetailingTransaction(
                1L,
                "234",
                184.42
        );
    }

    public static DataDetailingTransaction createDataDetailingTransactionValidUpdate() {
        return new DataDetailingTransaction(
                1L,
                "234",
                137.26
        );
    }
}
