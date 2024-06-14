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
    private UsernameRepository usernameRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.usernameRepository.findByLogin(login);
    }

    public DataUsername register(DataUserRegistration data){
        var emailAlreadyRegister = this.usernameRepository.existsByLogin(data.login());
        if (emailAlreadyRegister) {
            throw new ValidationException("E-mail was already register for another username!");
        }

        var passwordBCrypt = passwordEncoder.encode(data.password());
        var profiles = loadProfiles(data.admin());

        var username = new Username(data, passwordBCrypt, profiles);

        this.usernameRepository.save(username);

        return new DataUsername(username);
    }

    private List<Profile> loadProfiles(Boolean admin) {
        var profiles = new ArrayList<Profile>();

        if (admin != null && admin == true){
            var profileAdmin = profileRepository.findByName("ROLE_ADMIN");
            profiles.add(profileAdmin);
        }

        var profileUser = profileRepository.findByName("ROLE_USER");
        profiles.add(profileUser);

        return profiles;
    }
}
