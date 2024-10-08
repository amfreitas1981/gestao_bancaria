package com.banking.account.transact.domain.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("""
            select a
            from Account a
            where
            a.numeroConta = :numeroConta
            """)
    Account findAccountByAtivoTrue(String numeroConta);
}
