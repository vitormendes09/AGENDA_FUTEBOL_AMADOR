package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface IAuthController {
    @GetMapping("/login")
    String loginForm(Model model);
    
    @GetMapping("/register")
    String registerForm(Model model);
    
    @PostMapping("/register")
    String registerSubmit();
    
    @GetMapping("/logout")
    String logout();
}