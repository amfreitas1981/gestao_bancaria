package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.Account;

import static com.banking.account.transact.util.TransactionCreator.transactions;

public class AccountCreator {

    public static Account createAccountToBeSaved() {
        return new Account(
                null,
                "178964",
                405.83,
                true
        );
    }

    public static Account createAccountValid() {
        return new Account(
                1L,
                "164514",
                405.83,
                true
        );
    }

    public static Account createAccountValidUpdate() {
        return new Account(
                1L,
                "174591",
                919.56,
                true
        );
    }
}
