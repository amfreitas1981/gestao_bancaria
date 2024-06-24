package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.DataRegistrationAccount;

public class DataRegistrationAccountCreator {

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
