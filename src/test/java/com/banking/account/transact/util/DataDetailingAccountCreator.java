package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.DataDetailingAccount;

import java.math.BigDecimal;

public class DataDetailingAccountCreator {

    public static DataDetailingAccount createDataDetailingAccountToBeSaved(){
        return new DataDetailingAccount(
                null,
                "234",
                new BigDecimal(180.37)
        );
    }

    public static DataDetailingAccount createDataDetailingAccountValid(){
        return new DataDetailingAccount(
                1L,
                "234",
                new BigDecimal(180.37)
        );
    }

    public static DataDetailingAccount createDataDetailingAccountValidUpdate(){
        return new DataDetailingAccount(
                1L,
                "234",
                new BigDecimal(171.07)
        );
    }
}
