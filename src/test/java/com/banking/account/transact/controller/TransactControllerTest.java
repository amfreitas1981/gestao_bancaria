package com.banking.account.transact.controller;

import com.banking.account.transact.domain.transaction.DataDetailingTransaction;
import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import com.banking.account.transact.domain.transaction.PaymentForm;
import com.banking.account.transact.domain.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TransactControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactController transactController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        // Arrange
        DataRegistrationTransaction request = new DataRegistrationTransaction(
                PaymentForm.C, // Supondo que você tenha um enum PaymentForm com CREDIT_CARD
                new BigDecimal("100.00"),
                "123456789"
        );
        DataDetailingTransaction detailingTransaction = new DataDetailingTransaction(1L, "123456789", new BigDecimal("100.00"));

        when(transactionService.saveTransaction(any(DataRegistrationTransaction.class))).thenReturn(detailingTransaction);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost");

        // Act
        ResponseEntity<DataDetailingTransaction> response = transactController.createTransaction(request, uriBuilder);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(detailingTransaction, response.getBody());
    }
}

