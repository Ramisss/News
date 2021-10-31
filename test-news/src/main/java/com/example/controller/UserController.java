package com.example.controller;


import com.example.entity.User;
import com.example.payload.ApiResponse;
import com.example.payload.UserDto;
import com.example.repository.UserRepository;
import com.example.security.JwtTokenProvider;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class UserController {

    AuthenticationManager authenticationManager;

    UserService userService;

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    final
    JwtTokenProvider jwtTokenProvider;



    @PostMapping("register")
    public HttpEntity<?> register(@Valid @RequestBody UserDto userDto) {
        ApiResponse response = userService.registration(userDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("login")
    public HttpEntity<?> checkLogin(@RequestBody UserDto userDto) {
        try {
            Authentication checkedUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(),
                    userDto.getPassword()
            ));
            User user = (User) checkedUser.getPrincipal();
            String token = jwtTokenProvider.generateToken(user.getId());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(409).body("Login or password incorrect! \n Try again");
    }




}