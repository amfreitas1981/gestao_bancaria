package com.banking.account.transact.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
@SecurityRequirement(name = "bearer-key")
public class AccountController {

    // Criar métodos POST e GET

    @GetMapping
    public String helloConta(){
        return "Hello Conta!";
    }
}
