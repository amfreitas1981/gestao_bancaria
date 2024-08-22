package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.AccountNotFoundException;
import com.banking.account.transact.domain.NegativeBalanceException;
import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public DataDetailingTransaction saveTransaction(DataRegistrationTransaction data) {
        // Validações e regras de negócio
        if (data.numeroConta() == null) {
            throw new AccountNotFoundException("Conta não cadastrada na base de dados.");
        }

        var findAccount = accountRepository.findAccountByAtivoTrue(data.numeroConta());
        if (findAccount == null || !findAccount.getAtivo()) {
            throw new AccountNotFoundException("Conta não cadastrada na base de dados.");
        }

        Optional<List<Transaction>> optTransaction = Optional.ofNullable(transactionRepository.findByAccountNumeroConta(data.numeroConta()));

        BigDecimal valorCalculado = computeTaxTransaction(data);

        Optional<Account> optAccount = Optional.ofNullable(accountRepository.findAccountByAtivoTrue(data.numeroConta()));

        BigDecimal saldoInicial;

        saldoInicial = optAccount.get().getSaldo();

        BigDecimal saldo;
        saldo = saldoInicial.subtract(valorCalculado);

        // Incluir condição para validar se o saldo estiver menor que o valor para fazer a transação.
        int negativeBalance = saldo.compareTo(valorCalculado);
        if (negativeBalance < 0) { // Fazer algo parecido com isso...
            throw new NegativeBalanceException("Saldo insuficiente para efetuar a transação.");
        }

        var account = new Account(optAccount.get().getId(), data.numeroConta(), saldo.setScale(2, BigDecimal.ROUND_HALF_EVEN), true);
        accountRepository.save(account);

        var transaction = new Transaction(data, findAccount);
        transactionRepository.save(transaction);

        return new DataDetailingTransaction(transaction);
    }

    private BigDecimal computeTaxTransaction(DataRegistrationTransaction transactions) {
        if (transactions.formaPagamento() == null) {
            throw new ValidationException("É obrigatório informar a forma de pagamento para validar o processo de transação.");
        }
        BigDecimal taxTransaction;
        PaymentForm paymentForm = transactions.formaPagamento();

        taxTransaction = paymentForm.computeTaxTransaction(transactions.valor());

        return taxTransaction;
    }
}
