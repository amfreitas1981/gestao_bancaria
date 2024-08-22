package com.banking.account.transact.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.banking.account.transact.domain.username.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private Username username;

    private String secret = "test-secret";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(tokenService, "secret", secret);
    }

    @Test
    void testGenerateToken_Success() {
        when(username.getLogin()).thenReturn("user_login");

        String token = tokenService.generateToken(username);

        assertNotNull(token);
        assertDoesNotThrow(() -> {
            String subject = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("API banking.account")
                    .build()
                    .verify(token)
                    .getSubject();
            assertEquals("user_login", subject);
        });
    }

    @Test
    void testGenerateToken_ThrowsJWTCreationException() {
        when(username.getLogin()).thenReturn("user_login");

        // Configurar o segredo como nulo
        ReflectionTestUtils.setField(tokenService, "secret", null);

        // Tentar gerar o token e verificar a exceção
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                tokenService.generateToken(username)
        );

        // Verificar a mensagem da exceção
        assertEquals("Error to create JWT token", thrownException.getMessage());

        // Verificar se a causa da RuntimeException é a JWTCreationException
        Throwable cause = thrownException.getCause();
        assertNotNull(cause);
        assertInstanceOf(JWTCreationException.class, cause);
        assertEquals("The Secret cannot be null", cause.getMessage());
    }

    @Test
    void testGetSubject_Success() {
        String token = JWT.create()
                .withIssuer("API banking.account")
                .withSubject("user_login")
                .sign(Algorithm.HMAC256(secret));

        String subject = tokenService.getSubject(token);

        assertEquals("user_login", subject);
    }

    @Test
    void testGetSubject_ThrowsJWTVerificationException() {
        String token = JWT.create()
                .withIssuer("API banking.account")
                .withSubject("user_login")
                .sign(Algorithm.HMAC256(secret));

        ReflectionTestUtils.setField(tokenService, "secret", "wrong-secret");

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                tokenService.getSubject(token)
        );
        assertEquals("Invalid or expired JWT token!", exception.getMessage());
        assertTrue(exception.getCause() instanceof JWTVerificationException);
    }
}
