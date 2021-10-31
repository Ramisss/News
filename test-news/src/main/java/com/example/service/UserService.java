package com.example.service;


import com.example.entity.User;
import com.example.entity.enums.RoleName;
import com.example.payload.ApiResponse;
import com.example.payload.UserDto;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    final
    UserRepository userRepository;

    final
    PasswordEncoder passwordEncoder;

    final
    RoleRepository roleRepository;

    public UserService(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ApiResponse registration(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setAge(userDto.getAge());
            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_USER)));
            userRepository.save(user);
            return new ApiResponse("User saved", true);
        }
        User user1 = optionalUser.get();
        return new ApiResponse("User is exits!", false, user1);
    }
}
