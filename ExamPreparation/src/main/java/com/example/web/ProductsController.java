package com.example.web;

import com.example.models.binding.ProductBindingModel;
import com.example.models.service.ProductAddServiceModel;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ModelMapper modelMapper;
    private final ProductService productService;


    public ProductsController(ModelMapper modelMapper, ProductService productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        if (!model.containsAttribute("productBindingModel")){
            model.addAttribute("productBindingModel", new ProductBindingModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addProductConfig(@Valid ProductBindingModel productBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productBindingModel",productBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productBindingModel",bindingResult);
            return "redirect:add";
        }
        this.productService.addProduct(this.modelMapper.map(productBindingModel, ProductAddServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/all")
    public String all(Model model){
        return "/";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id){
        this.productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        this.productService.buyAll();
        return "redirect:/";
    }
}
