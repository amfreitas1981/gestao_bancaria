package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.transaction.Transaction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "contas")
@Entity(name = "Account")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    private int numero_conta;

    private Double saldo;

    private Boolean ativo;

    public Account(DataRegistrationAccount data){
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
        this.transactions = data.transactions().stream().map(Transaction::new).toList();
        this.transactions.forEach(d -> d.setAccount(this));
        /*
        * Campo chave, que pode ser utilizado para inativar uma conta, ao invés de remover em definitivo,
        * como aconteceria no Banco de Dados, pensando em deleção.
        */
        this.ativo = true;
    }
}
