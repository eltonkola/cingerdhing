package com.eltonkola.radioz.config;

import com.eltonkola.radioz.domain.Role;
import com.eltonkola.radioz.domain.User;
import com.eltonkola.radioz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InitialDataLoader {


    private UserRepository userRepository;

    @Autowired
    public InitialDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
        loadDefaultUsers();
    }

    private void loadDefaultUsers() {

        Optional<User> admin =  userRepository.findOneByEmail("admin@localhost.com");
         if(!admin.isPresent()){
            createDefaultAdminAccount();
        }

    }

    private void createDefaultAdminAccount() {


        String password = "admin";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);


        User admin = new User();
        admin.setEmail("admin@localhost.com");
        admin.setPasswordHash(hashedPassword);
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);
    }


}
