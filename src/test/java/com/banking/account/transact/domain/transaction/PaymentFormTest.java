package com.banking.account.transact.domain.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentFormTest {

    @Test
    @DisplayName("Conversão de String para Enum com sucesso, informando uma letra para identificar a forma de pagamento correta, prevista, nas regras de negócio")
    public void testFromStringSuccess(){
        assertNotNull(PaymentForm.P);
        assertNotNull(PaymentForm.valueOf("P"));
        assertEquals(PaymentForm.P, PaymentForm.fromString("P"));
        assertNotEquals("X", PaymentForm.P.name());

        assertNotNull(PaymentForm.C);
        assertNotNull(PaymentForm.valueOf("C"));
        assertEquals(PaymentForm.C, PaymentForm.fromString("C"));
        assertNotEquals("Y", PaymentForm.C.name());

        assertNotNull(PaymentForm.D);
        assertNotNull(PaymentForm.valueOf("D"));
        assertEquals(PaymentForm.D, PaymentForm.fromString("D"));
        assertNotEquals("Z", PaymentForm.D.name());
    }

    @Test
    @DisplayName("Conversão de String para Enum com falha, informando uma letra qualquer para tentar identificar alguma forma de pagamento, considerada inexistente para a regra de negócio")
    public void testFromStringFailure() {
        assertThrows(IllegalArgumentException.class, () -> PaymentForm.fromString("L"));
    }
}
