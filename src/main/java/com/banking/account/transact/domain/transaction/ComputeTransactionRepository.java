package com.banking.account.transact.domain.transaction;

public interface ComputeTransactionRepository {
    public double computeTaxTransaction(double valor);
}
