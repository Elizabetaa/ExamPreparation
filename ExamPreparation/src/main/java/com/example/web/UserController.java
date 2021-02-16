package com.example.web;

import com.example.models.binding.UserLoginBingingModel;
import com.example.models.binding.UserRegisterBindingModel;
import com.example.models.service.UserLoginServiceModel;
import com.example.models.service.UserRegisterServiceModel;
import com.example.security.CurrentUser;
import com.example.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        boolean isEqualsPasswords = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || !isEqualsPasswords){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

       this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBingingModel());
            model.addAttribute("notFound",false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm (@Valid UserLoginBingingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        boolean isExist = this.userService.authenticate(this.modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class));

        if (!isExist){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound",true);
            return "redirect:login";
        }
        this.currentUser.setUsername(userLoginBindingModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout (){
        this.currentUser.setUsername(null);
        return "redirect:/";
    }
}
