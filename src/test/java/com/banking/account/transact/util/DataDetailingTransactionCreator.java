package com.banking.account.transact.util;

import com.banking.account.transact.domain.transaction.DataDetailingTransaction;

import java.math.BigDecimal;

public class DataDetailingTransactionCreator {

    public static DataDetailingTransaction createDataDetailingTransactionToBeSaved() {
        return new DataDetailingTransaction(
                null,
                "234",
                new BigDecimal(184.42)
        );
    }

    public static DataDetailingTransaction createDataDetailingTransactionValid() {
        return new DataDetailingTransaction(
                1L,
                "234",
                new BigDecimal(184.42)
        );
    }

    public static DataDetailingTransaction createDataDetailingTransactionValidUpdate() {
        return new DataDetailingTransaction(
                1L,
                "234",
                new BigDecimal(137.26)
        );
    }
}
