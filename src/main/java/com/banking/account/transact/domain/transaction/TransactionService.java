package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountRepository;
import com.banking.account.transact.infra.exception.TreatErrors;
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

//    @Autowired
//    private static Transaction transaction;
//
//    private static double valor;


//    public TransactionService(Transaction transaction, double valor){
//        this.transaction = transaction;
//        this.valor = valor;
//    }

    public DataDetailingTransaction saveTransaction(Transaction transactions) {
        if (transactions.getNumero_conta() == null) {
            throw new ValidationException("Conta não cadastrada na base de dados.");
        }

        Optional<Transaction> optTransaction = Optional.ofNullable(transactionRepository.findTransactionByAtivoTrue(transactions.getNumero_conta()));

        var valorCalculado = computeTaxTransaction(transactions);

        Optional<Account> optAccount = Optional.ofNullable(accountRepository.findAccountByAtivoTrue(transactions.getNumero_conta()));

        double saldoInicial = 0.0;

        saldoInicial = optAccount.get().getSaldo();

        if (saldoInicial < valorCalculado) {
            throw new ValidationException("Transação não realizada. Saldo indisponível");
        } else {
            var saldo = saldoInicial - valorCalculado;

            var account = new Account(optAccount.get().getId(), transactions.getNumero_conta(), saldo, true);
//            var account = new Account(null, transactions.getNumero_conta(), saldo, true);
            var save = accountRepository.save(account);

            boolean existsTransaction = false;
            // Salvar a transação criada no Banco de Dados
            if (optTransaction.isPresent()) {
                if (!optTransaction.get().getId().equals(transactions.getId())){
                    existsTransaction = true;
                }
            }
            if (existsTransaction) {
                throw new ValidationException("Não houve registros de transações durante a operação. Id nulo ou inexistente.");
            } else {
                transactionRepository.save(transactions);
            }

            return new DataDetailingTransaction(save);
        }
    }

//    public Transaction saveTransaction(Transaction transactions){
//        boolean existsAccount = false;
//
//        Optional<Account> optAccount = Optional.ofNullable(accountRepository.findAccountByAtivoTrue(transactions.getNumero_conta()));
//
//        if (optAccount.isPresent()){
//            if (!optAccount.get().getNumero_conta().equals(transactions.getNumero_conta())){
//                existsAccount = true;
//            }
//        }
//
//        if (existsAccount){
//            throw new ValidationException("Conta inexistente.");
//        }
//
//        boolean existsTransaction = false;
//
//        Optional<Transaction> optTransaction = Optional.ofNullable(transactionRepository.findTransactionByAtivoTrue(transactions.getNumero_conta()));
//
//        if (optTransaction.isPresent()) {
//            if (!optTransaction.get().getId().equals(transactions.getId())) {
//                existsTransaction = true;
//            }
//        }
//
//        if (existsTransaction) {
//            throw new ValidationException("Impossível realizar a transação.");
//        } else {
//            transactionRepository.save(transactions);
//        }
//
//        if (optTransaction.get().getValor() > optAccount.get().getSaldo() || optTransaction.get().getValor().isNaN()) {
//            throw new ValidationException("Saldo insuficiente ou valor incorreto para realizar a transação.");
//        }
//
//        double calcTransaction = 0.0;
//
//        calcTransaction = optAccount.get().getSaldo() - computeTaxTransaction();
//
//        optAccount.get().getNumero_conta();
//        optAccount.get().setSaldo(calcTransaction);
//
//        return transactionRepository.save(transactions);
//    }

//    private Account calculateBalance(DataDetailingAccount detailingAccount) {}

    // Calcular a taxa da transação, após informar o valor
//    public double computeTaxTransaction() {
    private double computeTaxTransaction(Transaction transactions) {
        if (transactions.getForma_pagamento() == null) {
            throw new ValidationException("É obrigatório informar a forma de pagamento para validar o processo de transação.");
        }
        double taxTransaction = 0.0;
//        PaymentForm paymentForm = this.transaction.getForma_pagamento();
        PaymentForm paymentForm = transactions.getForma_pagamento();

//        taxTransaction = paymentForm.computeTaxTransaction(valor);
        taxTransaction = paymentForm.computeTaxTransaction(transactions.getValor());

        return taxTransaction;
    }
}
