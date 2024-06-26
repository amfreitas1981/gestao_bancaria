package com.banking.account.transact.domain.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String numero_conta;

    private Double saldo;

    private Boolean ativo;

    public Account(DataRegistrationAccount data){
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
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
