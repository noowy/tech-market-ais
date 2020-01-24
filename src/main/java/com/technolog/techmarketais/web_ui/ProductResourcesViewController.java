package com.technolog.techmarketais.web_ui;

import com.technolog.techmarketais.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductResourcesViewController
{

    private ProductsRepository prodRepo;

    @Autowired
    public ProductResourcesViewController(ProductsRepository prodRepo)
    {
        this.prodRepo = prodRepo;
    }

    @GetMapping("/")
    public String fetchMainPage(@RequestParam int pageNum, Model model)
    {

        return "prodMainPage";
    }
}
