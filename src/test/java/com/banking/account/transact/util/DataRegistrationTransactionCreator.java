package com.banking.account.transact.util;

import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import com.banking.account.transact.domain.transaction.PaymentForm;

import java.math.BigDecimal;

public class DataRegistrationTransactionCreator {

    public static DataRegistrationTransaction createDataRegistrationTransactionToBeSaved(){
        return new DataRegistrationTransaction(
                PaymentForm.P,
                new BigDecimal(10.01),
                "123"
        );
    }

    public static DataRegistrationTransaction createDataRegistrationTransactionValid(){
        return new DataRegistrationTransaction(
                PaymentForm.C,
                new BigDecimal(11.11),
                "456"
        );
    }

    public static DataRegistrationTransaction createDataRegistrationTransactionValidUpdate(){
        return new DataRegistrationTransaction(
                PaymentForm.D,
                new BigDecimal(11.11),
                "456"
        );
    }
}
