package com.example.services;

import com.example.models.service.UserLoginServiceModel;
import com.example.models.service.UserRegisterServiceModel;

public interface UserService {
    void register(UserRegisterServiceModel map);

    boolean authenticate(UserLoginServiceModel map);
}
