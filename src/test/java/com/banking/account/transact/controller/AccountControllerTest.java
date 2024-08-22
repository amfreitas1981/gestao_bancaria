package com.banking.account.transact.controller;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.AccountService;
import com.banking.account.transact.domain.accounts.DataDetailingAccount;
import com.banking.account.transact.domain.accounts.DataRegistrationAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @Autowired
    private JacksonTester<DataRegistrationAccount> dataRegistrationAccountJson;

    @Autowired
    private JacksonTester<DataDetailingAccount> dataDetailingAccountJson;

    @MockBean
    private AccountService accountService;

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
    void createAccountScenario2() throws Exception {
        DataRegistrationAccount dataRegistrationAccount = new DataRegistrationAccount(
                "235",
                new BigDecimal(180.38)
        );

        when(accountService.saveAccount(any())).thenReturn(Account.builder().build());

        var response = mvc.perform(
                post("/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataRegistrationAccountJson.write(dataRegistrationAccount).getJson())
        ).andReturn().getResponse();

        DataDetailingAccount dataDetailingAccount = new DataDetailingAccount(
                null,
                "235",
                new BigDecimal(180.38)
        );

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String jsonEsperado = dataDetailingAccountJson.write(dataDetailingAccount).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver código http 404, quando informações estão inválidas, conta não localizada")
    @WithMockUser
    void findAccountScenario1() throws Exception {
        mvc.perform(get("/conta"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Deveria devolver código http 200, quando informações estão inválidas")
    @WithMockUser
    void findAccountScenario2() throws Exception {

        DataDetailingAccount dataDetailingAccount = new DataDetailingAccount(
                null,
                "235",
                new BigDecimal(180.38)
        );

        Optional<Account> optionalAccount = Optional.of(new Account(null, "235", new BigDecimal(180.38), true));

        when(accountService.findAccount(any())).thenReturn(optionalAccount);

        var response = mvc.perform(
                get("/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataDetailingAccountJson.write(dataDetailingAccount).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonEsperado = dataDetailingAccountJson.write(dataDetailingAccount).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
