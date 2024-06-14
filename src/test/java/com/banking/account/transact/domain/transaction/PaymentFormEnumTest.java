package com.banking.account.transact.domain.transaction;

//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class PaymentFormEnumTest {
//
//    @Test
//    @DisplayName("Conversão de String para Enum com sucesso, informando uma letra para identificar a forma de pagamento correta, prevista, nas regras de negócio")
//    public void testFromStringSuccess(){
//        assertNotNull(PaymentFormEnum.P);
//        assertNotNull(PaymentFormEnum.valueOf("P"));
//        assertEquals(PaymentFormEnum.P, PaymentFormEnum.fromString("P"));
//        assertNotEquals("X", PaymentFormEnum.P.name());
//
//        assertNotNull(PaymentFormEnum.C);
//        assertNotNull(PaymentFormEnum.valueOf("C"));
//        assertEquals(PaymentFormEnum.C, PaymentFormEnum.fromString("C"));
//        assertNotEquals("Y", PaymentFormEnum.C.name());
//
//        assertNotNull(PaymentFormEnum.D);
//        assertNotNull(PaymentFormEnum.valueOf("D"));
//        assertEquals(PaymentFormEnum.D, PaymentFormEnum.fromString("D"));
//        assertNotEquals("Z", PaymentFormEnum.D.name());
//    }
//
//    @Test
//    @DisplayName("Conversão de String para Enum com falha, informando uma letra qualquer para tentar identificar alguma forma de pagamento, considerada inexistente para a regra de negócio")
//    public void testFromStringFailure() {
//        assertThrows(IllegalArgumentException.class, () -> PaymentFormEnum.fromString("L"));
//    }
//}
