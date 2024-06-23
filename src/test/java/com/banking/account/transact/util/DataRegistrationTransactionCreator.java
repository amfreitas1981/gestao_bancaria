package com.banking.account.transact.util;

import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import com.banking.account.transact.domain.transaction.PaymentForm;

public class DataRegistrationTransactionCreator {

    public static DataRegistrationTransaction createDataRegistrationTransactionToBeSaved(){
        return new DataRegistrationTransaction(
                null,
                PaymentForm.P,
                "234",
                10.01
        );
    }

    public static DataRegistrationTransaction createDataRegistrationTransactionValid(){
        return new DataRegistrationTransaction(
                1L,
                PaymentForm.C,
                "235",
                11.11
        );
    }

    public static DataRegistrationTransaction createDataRegistrationTransactionValidUpdate(){
        return new DataRegistrationTransaction(
                1L,
                PaymentForm.D,
                "236",
                12.12
        );
    }
}
