package com.banking.account.transact.controller;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    void up(){
        Account account = new Account();
        account.setId(1L);
        account.setNumero_conta("234");
        account.setSaldo(180.37);
        account.setAtivo(true);
        accountService.saveAccount(account);
    }

    @Test
    @DisplayName("Deveria devolver código http 400, quando informações estão inválidas")
    @WithMockUser
    void createAccountScenario1() throws Exception {
        var response = mvc.perform(post("/conta")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201, quando informações estão inválidas")
    @WithMockUser
    void createAccountScenario2() throws Exception {}

    @Test
    @DisplayName("Deveria devolver código http 404, quando informações estão inválidas, conta não localizada")
    @WithMockUser
    void findAccountScenario1() throws Exception {
        mvc.perform(get("/conta"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

//    @Test
//    @DisplayName("Deveria devolver código http 200, quando informações estão inválidas")
//    @WithMockUser
//    void findAccountScenario2() throws Exception {
//        mvc.perform(get("/conta")
//                .param("numero_conta", "123"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}