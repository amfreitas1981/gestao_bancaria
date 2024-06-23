package com.banking.account.transact.domain.accounts;

import jakarta.persistence.*;
import lombok.*;

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
