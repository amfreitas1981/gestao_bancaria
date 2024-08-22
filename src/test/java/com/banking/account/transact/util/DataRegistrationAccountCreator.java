package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.DataRegistrationAccount;

import java.math.BigDecimal;

public class DataRegistrationAccountCreator {

    public static DataRegistrationAccount createDataRegistrationAccountToBeSaved(){
        return new DataRegistrationAccount(
                "234",
                new BigDecimal(180.37)
        );
    }

    public static DataRegistrationAccount createDataRegistrationAccountValid(){
        return new DataRegistrationAccount(
                "235",
                new BigDecimal(180.38)
        );
    }

    public static DataRegistrationAccount createDataRegistrationAccountValidUpdate(){
        return new DataRegistrationAccount(
                "236",
                new BigDecimal(180.39)
        );
    }
}
