package com.banking.account.transact.domain.username;

import com.banking.account.transact.domain.ValidationException;
import com.banking.account.transact.domain.profile.Profile;
import com.banking.account.transact.domain.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsernameService implements UserDetailsService {

    @Autowired
    private UsernameRepository usuarioRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.usuarioRepository.findByLogin(login);
    }

    public DataUsername register(DataUserRegistration dados) {
        var emailJaCadastrado = this.usuarioRepository.existsByLogin(dados.login());
        if (emailJaCadastrado) {
            throw new ValidationException("E-mail já cadastrado para outro usuário!");
        }

        var senhaBCrypt = passwordEncoder.encode(dados.password());
        var perfis = carregarPerfis(dados.admin());

        var usuario = new Username(dados, senhaBCrypt, perfis);

        this.usuarioRepository.save(usuario);

        return new DataUsername(usuario);
    }

    private List<Profile> carregarPerfis(Boolean admin) {
        var perfis = new ArrayList<Profile>();

        if (admin != null && admin == true) {
            var perfilAdmin = profileRepository.findByNome("ROLE_ADMIN");
            perfis.add(perfilAdmin);
        }
        var perfilUser = profileRepository.findByNome("ROLE_USER");
        perfis.add(perfilUser);

        return perfis;
    }
}
