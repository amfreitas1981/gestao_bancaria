package com.banking.account.transact.controller;

import com.banking.account.transact.domain.username.*;
import com.banking.account.transact.infra.security.DataToken;
import com.banking.account.transact.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsernameService usernameService;

    @PostMapping
    public ResponseEntity efetuateLogin(@RequestBody @Valid DataAuthentication data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((Username) authentication.getPrincipal());

        return ResponseEntity.ok(new DataToken(tokenJWT));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DataUsername> register(@RequestBody @Valid DataUserRegistration data){
        var username = this.usernameService.register(data);

        return ResponseEntity.ok(username);
    }
}
