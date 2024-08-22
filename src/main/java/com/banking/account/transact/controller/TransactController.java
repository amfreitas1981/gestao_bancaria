package com.banking.account.transact.controller;

import com.banking.account.transact.domain.transaction.DataDetailingTransaction;
import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import com.banking.account.transact.domain.transaction.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("transacao")
@SecurityRequirement(name = "bearer-key")
public class TransactController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailingTransaction> createTransaction(@RequestBody @Valid DataRegistrationTransaction request, UriComponentsBuilder uriBuilder) {
        DataRegistrationTransaction transaction = new DataRegistrationTransaction(request);

        var dataDetailingTransaction = transactionService.saveTransaction(transaction);

        var uri = uriBuilder
                .path("/transacao/{id}")
                .buildAndExpand(dataDetailingTransaction)
                .toUri();

        return ResponseEntity.created(uri).body(dataDetailingTransaction);
    }
}
