package com.banking.account.transact.domain.accounts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select a
            from Account a
            where
            a.numero_conta = :numero_conta
            """)
    Account findAccountByAtivoTrue(String numero_conta);
}
