package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IAuthController;

@Controller
public class AuthController implements IAuthController {

    @Override
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("pageTitle", "Login - Peladas Amadoras");
        return "auth/login";
    }

    @Override
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("pageTitle", "Cadastro - Peladas Amadoras");
        return "auth/register";
    }

    @Override
    @PostMapping("/register")
    public String registerSubmit() {
        return "redirect:/login?success";
    }

    @Override
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}