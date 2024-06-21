package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.DataRegistrationAccount;
import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import com.banking.account.transact.domain.transaction.PaymentForm;

import java.util.Collections;
import java.util.List;

public class DataRegistrationAccountCreator {

    public static List<DataRegistrationTransaction> dataRegistrationTransaction = Collections.singletonList(new DataRegistrationTransaction(
            null,
            PaymentForm.P,
            "234",
            10.00
    ));

    public static DataRegistrationAccount createDataRegistrationAccountToBeSaved(){
        return new DataRegistrationAccount(
                "234",
                180.37
        );
    }

    public static DataRegistrationAccount createDataRegistrationAccountValid(){
        return new DataRegistrationAccount(
                "235",
                180.38
        );
    }

    public static DataRegistrationAccount createDataRegistrationAccountValidUpdate(){
        return new DataRegistrationAccount(
                "236",
                180.39
        );
    }
}
