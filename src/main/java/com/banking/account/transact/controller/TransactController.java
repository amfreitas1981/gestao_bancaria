package com.banking.account.transact.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
@SecurityRequirement(name = "bearer-key")
public class TransactController {

    // Criar método POST

    @GetMapping
    public String helloTransacao(){
        return "Hello Transacao!";
    }
}
