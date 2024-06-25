package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.DataDetailingAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "transacoes")
@Entity(name = "Transaction")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentForm forma_pagamento;

    private String numero_conta;

    private Double valor;

    private Double saldo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Account account;

    public Transaction(DataRegistrationTransaction data) {
        this.forma_pagamento = data.forma_pagamento();
        this.numero_conta = data.numero_conta();
        this.valor = data.valor();
    }

    public Transaction(DataDetailingAccount data) {
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
    }
}
