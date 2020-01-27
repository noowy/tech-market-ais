package com.technolog.techmarketais.auth;

import com.technolog.techmarketais.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController
{

    private UserRepository userRepo;
    private PasswordEncoder encoder;

    @Autowired
    public RegisterController(UserRepository userRepo, PasswordEncoder encoder)
    {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping
    public String registerForm(Model model)
    {
        model.addAttribute("form", new RegistrationForm());

        return "registerForm";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("form") RegistrationForm form, Errors errors, Model model)
    {
        if (errors.hasErrors())
            return "registerForm";

        if (!form.getPassword().equals(form.getConfirm()))
            return "redirect:/register?error=pass";

        try
        {
            userRepo.save(form.toUser(encoder));
        }
        catch (Exception e)
        {
            return "redirect:/register?error=user";
        }

        return "redirect:/";
    }
}
