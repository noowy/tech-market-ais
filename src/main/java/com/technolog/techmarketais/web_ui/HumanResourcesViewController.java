package com.technolog.techmarketais.web_ui;

import com.technolog.techmarketais.domain.hr.Employee;
import com.technolog.techmarketais.domain.hr.Position;
import com.technolog.techmarketais.repositories.EmployeeRepository;
import com.technolog.techmarketais.repositories.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/")
    public String fetchMainPage(@RequestParam int pageNum, Model model)
    {
        if (pageNum < 0)
        {
            model.addAttribute("error", "page_out_of_range");
            return "redirect:/hr/?pageNum=0";
        }

        PageRequest page = PageRequest.of(pageNum * 20, 20, Sort.by("firstName").ascending());
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

    @GetMapping("/emp/info/{id}")
    public String fetchEmpInfo(@PathVariable @Valid Long id, Model model)
    {
        log.info("THE ID IS -----------" + id.toString());
        Optional<Employee> emp = empRepo.findById(id);

        if (!emp.isPresent())
        {
            model.addAttribute("error", "not_exist");
            return "empInfoForm";
        }

        model.addAttribute("emp", emp.get());

        List<Position> positions = (List<Position>) posRepo.findAll();
        model.addAttribute("positions", positions);

        return "empInfoForm";
    }

    @GetMapping("/emp/add")
    public String fetchAddEmpForm(Model model)
    {
        List<Position> positions = (List<Position>) posRepo.findAll();
        model.addAttribute("positions", positions);
        return "addEmpForm";
    }

    @PostMapping("/emp/add")
    public String addNewEmployee(@ModelAttribute("emp") @Valid Employee emp)
    {
        Employee empAdded = empRepo.save(emp);
        return "redirect:/hr/emp/info/" + empAdded.getId().toString();
    }

    @GetMapping("/pos/info/{id}")
    public String fetchPosInfo(@PathVariable @Valid Long id, Model model)
    {
        log.info("THE ID IS -----------" + id.toString());
        Optional<Position> pos = posRepo.findById(id);

        if (!pos.isPresent())
        {
            model.addAttribute("error", "not_exist");
            return "posInfoForm";
        }

        model.addAttribute("pos", pos.get());
        return "posInfoForm";
    }

    @GetMapping("/pos/add")
    public String fetchAddPosForm()
    {
        return "addPosForm";
    }

    @PostMapping("/pos/add")
    public String addNewPosition(@ModelAttribute("pos") @Valid Position pos)
    {
        Position posAdded = posRepo.save(pos);
        return "redirect:/hr/pos/info/" + posAdded.getId().toString();
    }
}
