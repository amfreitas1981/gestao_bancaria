package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "contas")
@Entity(name = "Account")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Transaction transaction;
//    private List<Transaction> transactions;

    private String numero_conta;

    private Double saldo;

    private Boolean ativo;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Transaction transaction;

    public Account(DataRegistrationAccount data){
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
//        this.transactions = data.transactions().stream().map(Transaction::new).toList();
//        this.transactions.forEach(d -> d.setAccount(this));
        this.ativo = true;
    }

    public Account(DataDetailingAccount data){
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
    }

    public void deleteOrInvalidateInformations(){
        this.ativo = false;
    }
}
