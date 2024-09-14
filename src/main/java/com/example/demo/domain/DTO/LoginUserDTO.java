package com.example.demo.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public class LoginUserDTO {
    @NotBlank(message = "username không được để trống")
    private String username;
    @NotBlank(message = "password không được để trống")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
