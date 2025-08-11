package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IRegisterSubmitController;

@Controller
public class RegisterSubmitController implements IRegisterSubmitController {
    
    @Override
    public String handle(String nome, String email, String senha, String tipoUsuario, Model model) {
        // Lógica de registro será implementada posteriormente
        model.addAttribute("mensagem", "Registro realizado com sucesso! Faça login.");
        return "redirect:/login";
    }
}
