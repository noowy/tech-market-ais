package com.technolog.techmarketais.web_ui;

import com.technolog.techmarketais.domain.product_res.Item;
import com.technolog.techmarketais.repositories.ProductsRepository;
import com.technolog.techmarketais.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductResourcesViewController
{

    private ProductsRepository prodRepo;
    private WarehouseRepository whRepo;

    @Autowired
    public ProductResourcesViewController(ProductsRepository prodRepo, WarehouseRepository whRepo)
    {
        this.prodRepo = prodRepo;
        this.whRepo = whRepo;
    }

    @GetMapping("/")
    public String fetchMainPage(@RequestParam int pageNum, Model model)
    {
        if (pageNum < 0)
        {
            model.addAttribute("error", "page_out_of_range");
            return "prodMainPage";
        }

        PageRequest page = PageRequest.of(pageNum, 20, Sort.by("title").ascending());
        List<Item> prodList = prodRepo.findAll(page).getContent();
        model.addAttribute("prodList", prodList);

        return "prodMainPage";
    }
}
