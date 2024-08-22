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

import java.math.BigDecimal;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_conta")
    private String numeroConta;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "ativo")
    private Boolean ativo;

    public Account(DataDetailingAccount data){
        this.numeroConta = data.numeroConta();
        this.saldo = data.saldo();
    }

    public Account(DataRegistrationAccount data) {
        this.numeroConta = data.numeroConta();
        this.saldo = data.saldo();
    }

    public void deleteOrInvalidateInformations(){
        this.ativo = false;
    }
}
