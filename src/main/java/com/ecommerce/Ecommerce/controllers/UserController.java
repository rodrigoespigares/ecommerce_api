package com.ecommerce.Ecommerce.controllers;


import com.ecommerce.Ecommerce.components.JwtTokenProvider;
import com.ecommerce.Ecommerce.dto.ChangePasswordRequest;
import com.ecommerce.Ecommerce.dto.PorfileRequest;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.dto.LoginRequest;
import com.ecommerce.Ecommerce.services.EmailService;
import com.ecommerce.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> registro(@RequestBody User user){
        Map<String, String> response = new HashMap<>();
        try{
            if (user.getUsuario() == null || user.getEmail() == null) {
                response.put("status", "error");
                response.put("message", "Ocurrió un error al registrar el usuario.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            String plainPassword = generateRandomPassword(10);

            emailService.sendEmail(user.getEmail(),"Confirmación de registro", "Gracias por registrarte en Pockommers. Tu contraseña es: "+ plainPassword+".");

            user.setPassword(plainPassword);
            userService.saveUser(user);

            response.put("status", "success");
            response.put("message", "Usuario creado. Revisa tu email para encontrar la contraseña.");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Ocurrió un error al registrar el usuario.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        Map<String, String> response = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            String token = jwtTokenProvider.generateToken(userDetails);

            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Map<String, Object>> profile(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<PorfileRequest> userDtoOpt = userService.findUserById(id);

            if (userDtoOpt.isPresent()) {
                response.put("status", "success");
                response.put("data", userDtoOpt.get());
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Usuario no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Ocurrió un error al buscar el usuario.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Map<String, String>> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        Optional<User> userOpt = userService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Usuario no encontrado"));
        }

        User user = userOpt.get();

        // Verificar contraseña actual
        if (!passwordEncoder.matches(request.getPassword_actual(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "La contraseña actual es incorrecta"));
        }

        // Establecer nueva contraseña
        user.setPassword(passwordEncoder.encode(request.getNew_password()));
        userService.save(user);

        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente"));
    }


    private String generateAndEncryptPassword(String plainPassword) {

        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
