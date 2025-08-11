package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.ILoginSubmitController;

@Controller
public class LoginSubmitController  implements ILoginSubmitController{

    @Override
    public String handle(String email, String senha, Model model) {
        // Lógica de autenticação será implementada posteriormente
        return "redirect:/principal";
    }
    
}
