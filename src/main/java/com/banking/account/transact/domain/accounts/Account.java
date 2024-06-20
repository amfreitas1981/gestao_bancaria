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
        /*
        * Campo chave, que pode ser utilizado para inativar uma conta, ao invés de remover em definitivo,
        * como aconteceria no Banco de Dados, pensando em deleção.
        */
        this.ativo = true;
    }

    public Account(DataDetailingAccount data){
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
    }

    /*
     * Este método, mesmo que não seja utilizado para o desafio proposto, foi criado, pensando no
     * sentido de remover uma conta, apenas para inativar (alterando de true, para false), ao validar
     * quando verificar ou consultar no Banco de Dados, sem precisar remover de fato, permitindo reativar,
     * quando precisar.
     */
    public void deleteOrInvalidateInformations(){
        this.ativo = false;
    }
}
