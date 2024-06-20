package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private static Transaction transaction;

    private static double valor;


    public TransactionService(Transaction transaction, double valor){
        this.transaction = transaction;
        this.valor = valor;
    }

    public Transaction saveTransaction(Transaction transactions){
        boolean existsAccount = false;

        Optional<Account> optAccount = Optional.ofNullable(accountRepository.findAccountByAtivoTrue(transactions.getNumero_conta()));

        if (optAccount.isPresent()){
            if (!optAccount.get().getId().equals(transactions.getId())){
                existsAccount = true;
            }
        }

        if (existsAccount){
            throw new ValidationException("Conta inexistente.");
        }

        boolean existsTransaction = false;

        Optional<Transaction> optTransaction = Optional.ofNullable(transactionRepository.findTransactionByAtivoTrue(transactions.getNumero_conta()));

        if (optTransaction.isPresent()) {
            if (optTransaction.get().getNumero_conta().equals(transactions.getNumero_conta())) {
                existsTransaction = true;
            }
        }

        if (existsTransaction){
            throw new ValidationException("Impossível realizar a transação.");
        }

        if (optTransaction.get().getValor() > optAccount.get().getSaldo()) {
            throw new ValidationException("Saldo insuficiente para gerar a transação.");
        }

        double calcTransaction = 0.0;

        calcTransaction = optAccount.get().getSaldo() - computeTransaction();

        optAccount.get().getNumero_conta();
        optAccount.get().setSaldo(calcTransaction);

        return transactionRepository.save(transactions);
    }

    public double computeTransaction() {
        double taxTransaction = 0.0;
        PaymentForm paymentForm = this.transaction.getForma_pagamento();

        taxTransaction = paymentForm.computeTaxTransaction(valor);

        return taxTransaction;
    }
}
