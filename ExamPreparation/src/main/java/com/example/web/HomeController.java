package com.example.web;

import com.example.models.entities.enums.CategoryNameEnum;
import com.example.models.view.ProductView;
import com.example.security.CurrentUser;
import com.example.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    private String index(Model model) {
        if (currentUser.isAnonymous()){
            return "index";
        }

        if (productService.totalPrice() == null){
            model.addAttribute("totalPrice","0");
        }else {
            model.addAttribute("totalPrice", productService.totalPrice());
        }

        model.addAttribute("foods",this.productService.getProductsByCategory(CategoryNameEnum.FOOD));
        model.addAttribute("drinks",this.productService.getProductsByCategory(CategoryNameEnum.DRINK));
        model.addAttribute("households",this.productService.getProductsByCategory(CategoryNameEnum.HOUSEHOLD));
        model.addAttribute("others",this.productService.getProductsByCategory(CategoryNameEnum.OTHER));

        System.out.println();
        return "home";
    }
}
