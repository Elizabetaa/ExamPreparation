package com.example.services.impl;

import com.example.models.entities.User;
import com.example.models.service.UserLoginServiceModel;
import com.example.models.service.UserRegisterServiceModel;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = this.modelMapper.map(userRegisterServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean authenticate(UserLoginServiceModel userLoginServiceModel) {
        User user = this.userRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(), userLoginServiceModel.getPassword())
                .orElse(null);
        return user != null;
    }
}
