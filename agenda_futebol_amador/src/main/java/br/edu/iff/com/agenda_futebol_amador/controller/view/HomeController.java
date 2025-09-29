package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Agenda Futebol Amador");
        return "index";
    }

    @GetMapping("/swagger-ui")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}