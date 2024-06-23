package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        boolean existsAccount = false;

        Optional<Account> optAccount = Optional.ofNullable(accountRepository.findAccountByAtivoTrue(account.getNumero_conta()));

        if (optAccount.isPresent()){
            if (!optAccount.get().getId().equals(account.getId())){
                existsAccount = true;
            }
        }

        if (existsAccount){
            throw new ValidationException("Conta já cadastrada!");
        }

        return accountRepository.save(account);
    }

    public Optional<Account> findAccount(String numero_conta) {
        return Optional.ofNullable(accountRepository.findAccountByAtivoTrue(numero_conta));
    }
}
