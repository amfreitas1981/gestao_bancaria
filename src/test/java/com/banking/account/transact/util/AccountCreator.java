package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.Account;

import java.math.BigDecimal;

public class AccountCreator {

    public static Account createAccountToBeSaved() {
        return new Account(
                null,
                "178964",
                new BigDecimal(405.83),
                true
        );
    }

    public static Account createAccountValid() {
        return new Account(
                1L,
                "235",
                new BigDecimal(180.38),
                true
        );
    }

    public static Account createAccountValidUpdate() {
        return new Account(
                1L,
                "174591",
                new BigDecimal(919.56),
                true
        );
    }
}
