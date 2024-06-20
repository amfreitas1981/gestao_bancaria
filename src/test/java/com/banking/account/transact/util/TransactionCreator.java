package com.banking.account.transact.util;

//import com.banking.account.transact.domain.accounts.Account;
//import com.banking.account.transact.domain.transaction.PaymentForm;
//import com.banking.account.transact.domain.transaction.Transaction;
//
//import java.util.Collections;
//import java.util.List;
//
//public class TransactionCreator {
//
//    public static List<Transaction> transactions = Collections.singletonList(new Transaction(
//            3L,
//            Account.builder().build(),
//            PaymentForm.C,
//            1564,
//            40
//    ));
//
//    public static Account account = new Account(
//            1L,
//            transactions,
//            147,
//            185.56,
//            true
//    );
//
//    public static Transaction createTransactionToBeSaved(){
//        return new Transaction(
//                null,
//                account,
//                PaymentForm.P,
//                123,
//                10
//        );
//    }
//
//    public static Transaction createTransactionValid(){
//        return new Transaction(
//                1L,
//                account,
//                PaymentForm.P,
//                123,
//                10
//        );
//    }
//
//    public static Transaction createTransactionValidUpdate(){
//        return new Transaction(
//                1L,
//                account,
//                PaymentForm.D,
//                111,
//                11
//        );
//    }
//}
