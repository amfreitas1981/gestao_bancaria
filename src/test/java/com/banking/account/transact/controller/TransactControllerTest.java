package com.banking.account.transact.controller;

//import com.banking.account.transact.domain.transaction.DataDetailingTransaction;
//import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
//import com.banking.account.transact.domain.transaction.PaymentForm;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//class TransactControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private JacksonTester<DataRegistrationTransaction> dataRegistrationTransactionJson;
//
//    @Autowired
//    private JacksonTester<DataDetailingTransaction> dataDetailingTransactionJson;
//
////    @MockBean
////    private TransactionService transactionService;
////
////    @MockBean
////    private AccountService accountService;
//
//    @Test
//    @DisplayName("Deveria devolver código http 404, quando informações estão inválidas")
//    @WithMockUser
//    void createTransactionScenario1() throws Exception {}
//
//    @Test
//    @DisplayName("Deveria devolver código http 201, quando informações estão inválidas")
//    @WithMockUser
//    void createTransactionScenario2() throws Exception {
//        DataRegistrationTransaction dataRegistrationTransaction = new DataRegistrationTransaction(
//                null,
//                PaymentForm.C,
//                "235",
//                11.11
//        );
//
////        when(transactionService.saveTransaction(any())).thenReturn(dataRegistrationTransaction);
//
//        var response = mvc
//                .perform(
//                        post("/transacao")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(dataRegistrationTransactionJson.write(dataRegistrationTransaction).getJson())
//                )
//                .andReturn().getResponse();
//
//        DataDetailingTransaction dataDetailingTransaction = new DataDetailingTransaction(
//                null,
//                "235",
//                184.42
//        );
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//
//        String jsonEsperado = dataDetailingTransactionJson.write(dataDetailingTransaction).getJson();
//
//        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
//    }
//}
