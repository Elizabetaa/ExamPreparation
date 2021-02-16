package com.example.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBingingModel {

    private String username;
    private String password;

    public UserLoginBingingModel() {
    }

    @NotBlank(message = "Username cannot be empty string")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }

    public UserLoginBingingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank(message = "Password cannot be empty string")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    public String getPassword() {
        return password;
    }

    public UserLoginBingingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
