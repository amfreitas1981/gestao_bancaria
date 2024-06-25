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
            double saldo = 0.0;
            saldo = saldoInicial - valorCalculado;

            var account = new Account(optAccount.get().getId(), transactions.getNumero_conta(), saldo, true);
            var saveAccount = accountRepository.save(account);

            boolean existsTransaction = false;
            // Salvar a transação criada no Banco de Dados
            if (optTransaction.isPresent()) {
                if (!optTransaction.get().getId().equals(transactions.getId())) {
                    existsTransaction = true;
                }
            }

            Transaction saveTransaction;

            if (existsTransaction) {
                throw new ValidationException("Não houve registros de transações durante a operação. Id nulo ou inexistente.");
            } else {
                var transaction = new Transaction(transactions.getId(), transactions.getForma_pagamento(), transactions.getNumero_conta(), transactions.getValor(), saldo, account);
                saveTransaction = transactionRepository.save(transaction);
            }

            return new DataDetailingTransaction(saveTransaction);
        }
    }

    private double computeTaxTransaction(Transaction transactions) {
        if (transactions.getForma_pagamento() == null) {
            throw new ValidationException("É obrigatório informar a forma de pagamento para validar o processo de transação.");
        }
        double taxTransaction = 0.0;
        PaymentForm paymentForm = transactions.getForma_pagamento();

        taxTransaction = paymentForm.computeTaxTransaction(transactions.getValor());

        return taxTransaction;
    }
}
