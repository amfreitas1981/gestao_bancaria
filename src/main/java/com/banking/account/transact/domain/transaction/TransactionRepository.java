package com.banking.account.transact.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("""
            select t
            from Transaction t
            where
            t.numero_conta = :numero_conta
            """)
    Transaction findTransactionByAtivoTrue(String numero_conta);
}
