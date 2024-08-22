package com.banking.account.transact.controller;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountService;
import com.banking.account.transact.domain.accounts.DataDetailingAccount;
import com.banking.account.transact.domain.accounts.DataRegistrationAccount;
import com.banking.account.transact.infra.exception.TreatErrors;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("conta")
@SecurityRequirement(name = "bearer-key")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailingAccount> createAccount(@RequestBody @Valid DataRegistrationAccount request, UriComponentsBuilder uriBuilder) {
        var account = new Account(request);
        accountService.saveAccount(account);
        var uri = uriBuilder
                .path("/conta/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(new DataDetailingAccount(account));
    }

    @GetMapping
    public ResponseEntity<DataDetailingAccount> findAccount(String numeroConta) {
        Optional<Account> optionalAccount = accountService.findAccount(numeroConta);

        if (optionalAccount.isEmpty()) {
            return new TreatErrors().treatError404();
        }

        return ResponseEntity.ok(new DataDetailingAccount(optionalAccount.get()));
    }
}
