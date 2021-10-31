package com.example.components;


import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.enums.RoleName;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {

            Role roleAdmin = roleRepository.save(new Role(1, RoleName.ROLE_ADMIN));
            Role roleUser = roleRepository.save(new Role(2, RoleName.ROLE_USER));

            User user1 = new User();
            user1.setFirstName("Admin");
            user1.setUserName("Admin");
            user1.setEmail("admin@gmail.com");
            user1.setPassword(passwordEncoder.encode("root123"));
            user1.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)));
            user1.setEnabled(true);
            user1.setAge((byte) 33);
            userRepository.save(user1);

        }
    }
}



















