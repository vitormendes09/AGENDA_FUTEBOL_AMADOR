package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.ILoginFormController;

@Controller
public class LoginFormController implements ILoginFormController {

    @Override
    public String handle(Model model) {
        return "login";
    }

    
}