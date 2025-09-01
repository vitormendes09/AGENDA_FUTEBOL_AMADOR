package br.edu.iff.com.agenda_futebol_amador.controller.view;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IHomeController;

@Controller
public class HomeController implements IHomeController {

    @Override
    public String home(Model model) { 
        model.addAttribute("partidas");
        return "index";
    }
    
}