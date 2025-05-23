package com.ecommerce.Ecommerce.dto;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ChangePasswordRequest {

    private String password_actual;
    private String new_password;

    public String getPassword_actual() {
        return password_actual;
    }

    public void setPassword_actual(String password_actual) {
        this.password_actual = password_actual;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new BCryptPasswordEncoder().encode(new_password);
    }
}

