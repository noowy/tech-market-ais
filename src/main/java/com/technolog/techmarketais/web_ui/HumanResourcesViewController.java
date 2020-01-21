package com.technolog.techmarketais.web_ui;

import com.technolog.techmarketais.domain.hr.Employee;
import com.technolog.techmarketais.domain.hr.Position;
import com.technolog.techmarketais.repositories.EmployeeRepository;
import com.technolog.techmarketais.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/hr")
public class HumanResourcesViewController
{
    private EmployeeRepository empRepo;
    private PositionRepository posRepo;

    @Autowired
    public HumanResourcesViewController(EmployeeRepository empRepo, PositionRepository posRepo)
    {
        this.empRepo = empRepo;
        this.posRepo = posRepo;
    }

    @GetMapping("/")
    public String fetchMainPage()
    {
        return "hrMainForm";
    }

    @PostMapping("/add_emp")
    public String addNewEmployee(@Valid Employee emp)
    {
        empRepo.save(emp);
        return "redirect:/hr?emp_added=true";
    }

    @PostMapping("/add_pos")
    public String addNewPosition(@Valid Position pos)
    {
        posRepo.save(pos);
        return "redirect:/hr?pos_added=true";
    }

    @DeleteMapping("/del_emp")
    public String removeEmployee(@Valid Employee emp)
    {
        empRepo.delete(emp);
        return "redirect:/hr?emp_deleted=true";
    }

    @DeleteMapping("/del_pos")
    public String removePosition(@Valid Position pos)
    {
        posRepo.delete(pos);
        return "redirect:/hr?pos_deleted=true";
    }
}
