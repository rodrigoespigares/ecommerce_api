package com.ecommerce.Ecommerce.models;

import com.ecommerce.Ecommerce.dto.PorfileRequest;
import org.springframework.security.crypto.bcrypt.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String email;
    private String password;
    private Boolean is_validated;
    private String api_key;
    private int permission;

    public User(){
        this.password = "";
        this.api_key = "";
        this.is_validated = false;
        this.permission = 0;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);;
    }

    public Boolean getIs_validated() {
        return is_validated;
    }

    public void setIs_validated(Boolean is_validated) {
        this.is_validated = is_validated;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }


    public PorfileRequest toUserDto(){
        PorfileRequest userDto = new PorfileRequest();

        userDto.setId(this.getId());
        userDto.setApi_key(this.getApi_key());
        userDto.setEmail(this.getEmail());
        userDto.setUsuario(this.getUsuario());
        userDto.setIs_validated(this.getIs_validated());

        return userDto;
    }
}
