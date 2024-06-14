package com.banking.account.transact.domain.username;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsernameRepository extends JpaRepository<Username, Long> {
    Username findByLogin(String login);
    boolean existsByLogin(String email);
}
