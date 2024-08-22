package com.banking.account.transact.domain.transaction;

import java.math.BigDecimal;

public interface ComputeTransactionRepository {
    public BigDecimal computeTaxTransaction(BigDecimal valor);
}
