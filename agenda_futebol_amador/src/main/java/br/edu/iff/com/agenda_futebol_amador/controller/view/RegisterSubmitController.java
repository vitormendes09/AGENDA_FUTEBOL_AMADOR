package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IRegisterSubmitController;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IRegisterSubmitService;

@Controller
public class RegisterSubmitController implements IRegisterSubmitController {
    
    private final IRegisterSubmitService registerService;

    // Injeção de dependência via construtor
    public RegisterSubmitController(IRegisterSubmitService registerService) {
        this.registerService = registerService;
    }
    
    @Override
    public String handle(String nome, String email, String senha, String tipoUsuario, Model model) {
        try {
            IUsuario usuario = registerService.perform(nome, email, senha, tipoUsuario);
            model.addAttribute("mensagem", "Registro realizado com sucesso! Faça login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "registro"; 
        }
    }
}
