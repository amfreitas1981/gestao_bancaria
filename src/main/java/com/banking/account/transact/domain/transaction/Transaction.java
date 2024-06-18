package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.DataDetailingAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "contas_id")
//    private Account account;

    @Enumerated(EnumType.STRING)
    private PaymentFormEnum forma_pagamento;

    private String numero_conta;

    private Double valor;

    private Double saldo;

    public Transaction(DataRegistrationTransaction data) {
        this.forma_pagamento = data.forma_pagamento();
        this.numero_conta = data.numero_conta();
        this.valor = data.valor();
    }

    public Transaction(DataDetailingAccount data) {
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
    }

//    public void setAccount(Account account) {
//        this.account = account;
//    }
}
