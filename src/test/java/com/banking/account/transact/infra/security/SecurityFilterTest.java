package com.banking.account.transact.infra.security;

import com.banking.account.transact.domain.username.Username;
import com.banking.account.transact.domain.username.UsernameRepository;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

class SecurityFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UsernameRepository usernameRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private SecurityFilter securityFilter;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws IOException, ServletException {
        // Arrange
        String token = "valid.token";
        String subject = "testUser";
        Username user = mock(Username.class);

        // Configurar mocks
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.getSubject(token)).thenReturn(subject);
        when(usernameRepository.findByLogin(subject)).thenReturn(user);
        when(user.getAuthorities()).thenReturn(null); // Ajuste conforme necessário

        // Act
        securityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(tokenService, times(1)).getSubject(token);
        verify(usernameRepository, times(1)).findByLogin(subject);
        verify(filterChain, times(1)).doFilter(request, response);

        // Verificar se o contexto de segurança foi atualizado corretamente
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(user, authentication.getPrincipal());
        assertNull(authentication.getCredentials());
    }

    @Test
    void testDoFilterInternal_WithNoToken() throws IOException, ServletException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        securityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(tokenService, never()).getSubject(anyString());
        verify(usernameRepository, never()).findByLogin(anyString());
        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
