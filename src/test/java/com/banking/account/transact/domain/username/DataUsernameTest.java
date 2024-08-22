package com.banking.account.transact.domain.username;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataUsernameTest {

    @Test
    void testDataUsernameWithUsernameConstructor() {
        // Criando um mock da classe Username
        Username usernameMock = mock(Username.class);

        // Definindo o comportamento do mock
        when(usernameMock.getId()).thenReturn(1L);
        when(usernameMock.getNome()).thenReturn("John Doe");
        when(usernameMock.getLogin()).thenReturn("johndoe");
        when(usernameMock.isAdmin()).thenReturn(true);

        // Criando uma instância de DataUsername usando o construtor que recebe um Username
        DataUsername dataUsername = new DataUsername(usernameMock);

        // Verificando se os valores foram corretamente atribuídos
        assertEquals(1L, dataUsername.id());
        assertEquals("John Doe", dataUsername.nome());
        assertEquals("johndoe", dataUsername.login());
        assertTrue(dataUsername.admin());
    }
}
