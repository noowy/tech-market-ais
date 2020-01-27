package com.technolog.techmarketais.auth;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

public class RegistrationForm
{

    @NotBlank(message = "Username is required")
    private String username;

    @Length(min = 4, message = "Password must be at least 4 characters long")
    private String password;
    private String confirm;

    private String fullname;

    public RegistrationForm()
    {
        username = null;
        password = null;
        fullname = null;
        confirm = null;
    }

    public RegistrationForm(String username, String password, String confirm, String fullname)
    {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.confirm = confirm;
    }

    public User toUser(PasswordEncoder passwordEncoder)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullname);
        return user;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getConfirm()
    {
        return confirm;
    }

    public void setConfirm(String confirm)
    {
        this.confirm = confirm;
    }
}
