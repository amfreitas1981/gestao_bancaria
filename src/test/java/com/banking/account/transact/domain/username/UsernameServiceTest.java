package com.banking.account.transact.domain.username;

import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.profile.Profile;
import com.banking.account.transact.domain.profile.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsernameServiceTest {

    @Mock
    private UsernameRepository usuarioRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsernameService usernameService;

    private DataUserRegistration dados;

    @BeforeEach
    void setup() {
        dados = new DataUserRegistration("Nome Teste", "test@example.com", "senha123", false);
    }

    @Test
    void deveCarregarUsuarioPorUsername() {
        // Arrange
        var username = new Username(dados, "encodedPassword", List.of());
        when(usuarioRepository.findByLogin(dados.login())).thenReturn(username);

        // Act
        var result = usernameService.loadUserByUsername(dados.login());

        // Assert
        assertNotNull(result);
        assertEquals(username.getLogin(), result.getUsername());
        verify(usuarioRepository).findByLogin(dados.login());
    }

    @Test
    void deveRegistrarNovoUsuario() {
        // Arrange
        when(usuarioRepository.existsByLogin(dados.login())).thenReturn(false);
        when(passwordEncoder.encode(dados.password())).thenReturn("encodedPassword");
        when(profileRepository.findByNome("ROLE_USER")).thenReturn(new Profile());

        // Act
        var result = usernameService.register(dados);

        // Assert
        assertNotNull(result);
        assertEquals(dados.login(), result.login());
        verify(usuarioRepository).save(any(Username.class));
    }

    @Test
    void deveLancarValidationExceptionQuandoEmailJaEstiverCadastrado() {
        // Arrange
        when(usuarioRepository.existsByLogin(dados.login())).thenReturn(true);

        // Act & Assert
        assertThrows(ValidationException.class, () -> usernameService.register(dados));
        verify(usuarioRepository, never()).save(any(Username.class));
    }

    @Test
    void deveRegistrarUsuarioComPerfilAdmin() {
        // Arrange
        var adminDados = new DataUserRegistration("Admin User", "admin@example.com", "senha123", true);
        when(usuarioRepository.existsByLogin(adminDados.login())).thenReturn(false);
        when(passwordEncoder.encode(adminDados.password())).thenReturn("encodedPassword");
        when(profileRepository.findByNome("ROLE_USER")).thenReturn(new Profile());
        when(profileRepository.findByNome("ROLE_ADMIN")).thenReturn(new Profile());

        // Act
        var result = usernameService.register(adminDados);

        // Assert
        assertNotNull(result);
        verify(profileRepository).findByNome("ROLE_ADMIN");
        verify(usuarioRepository).save(any(Username.class));
    }

    @Test
    void deveCodificarSenhaAntesDeSalvarUsuario() {
        // Arrange
        when(usuarioRepository.existsByLogin(dados.login())).thenReturn(false);
        when(passwordEncoder.encode(dados.password())).thenReturn("encodedPassword");
        when(profileRepository.findByNome("ROLE_USER")).thenReturn(new Profile());

        // Act
        usernameService.register(dados);

        // Assert
        verify(passwordEncoder).encode(dados.password());
        verify(usuarioRepository).save(any(Username.class));
    }
}
