package com.example.project_stock.controller;


import com.example.project_stock.domain.user.AuthenticationDTO;
import com.example.project_stock.domain.user.LoginResponseDTO;
import com.example.project_stock.domain.user.RegisterDTO;
import com.example.project_stock.domain.user.User;
import com.example.project_stock.infra.security.TokenService;
import com.example.project_stock.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("auth")


public class AuthenticatorController {
    @Autowired
    private AuthenticationManager authenticatorManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassowrd = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = authenticatorManager.authenticate(usernamePassowrd);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }




}
