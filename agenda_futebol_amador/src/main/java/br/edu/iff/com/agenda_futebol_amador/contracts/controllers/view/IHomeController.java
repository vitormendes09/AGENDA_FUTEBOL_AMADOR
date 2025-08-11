package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public interface IHomeController {
    @GetMapping(path = "/")
    String home(Model model);
    
} 
