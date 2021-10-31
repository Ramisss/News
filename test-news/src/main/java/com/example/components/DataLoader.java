package com.example.components;


import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.enums.RoleName;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    UserRepository userRepository;


    PasswordEncoder passwordEncoder;

    RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


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
            user1.setAge("33");
            userRepository.save(user1);

        }
    }
}



















