package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.ICriarPartidaFormController;

public class CriarPartidasFormController implements ICriarPartidaFormController{
     @Override
    public String handle(Model model) {
        return "partidas/form";
    }
}
