package com.technolog.techmarketais.web_ui;

import com.technolog.techmarketais.domain.hr.Employee;
import com.technolog.techmarketais.domain.hr.Position;
import com.technolog.techmarketais.repositories.EmployeeRepository;
import com.technolog.techmarketais.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String fetchMainPage(@RequestParam int pageNum, Model model)
    {
        if (pageNum < 0)
        {
            model.addAttribute("error", "page_out_of_range");
            return "hrMainForm";
        }

        PageRequest page = PageRequest.of(pageNum, 20, Sort.by("firstName").ascending());
        List<Employee> emps = empRepo.findAll(page).getContent();
        model.addAttribute("empList", emps);

        return "hrMainForm";
    }

    @GetMapping(value = "/", params = "action=search")
    public String fetchMainPage(@RequestParam String query, Model model)
    {
        String[] names = query.split("\\s");
        List<Employee> suggestions = new ArrayList<>();

        for (String name : names)
            suggestions.addAll(empRepo.findEmployeeByLastName(name));
        model.addAttribute("empList", suggestions);

        return "hrMainForm";
    }

    @GetMapping("/emp/{id}")
    public String fetchEmpInfo(@PathVariable @Valid Long id, Model model)
    {
        Optional<Employee> emp = empRepo.findById(id);

        if (!emp.isPresent())
        {
            model.addAttribute("error", "not_exist");
            return "empInfoForm";
        }

        model.addAttribute("emp", emp.get());
        return "empInfoForm";
    }

    @PostMapping("/emp/add")
    public String addNewEmployee(@RequestBody @Valid Employee emp)
    {
        Employee empAdded = empRepo.save(emp);
        return "redirect:/hr/emp/" + empAdded.getId().toString();
    }

    @GetMapping("/pos/{id}")
    public String fetchPosInfo(@PathVariable @Valid Long id, Model model)
    {
        Optional<Position> pos = posRepo.findById(id);

        if (!pos.isPresent())
        {
            model.addAttribute("error", "not_exist");
            return "posInfoForm";
        }

        model.addAttribute("pos", pos);
        return "posInfoForm";
    }

    @PostMapping("/pos/add")
    public String addNewPosition(@RequestBody @Valid Position pos)
    {
        Position posAdded = posRepo.save(pos);
        return "redirect:/hr/pos/" + posAdded.getId().toString();
    }
}
