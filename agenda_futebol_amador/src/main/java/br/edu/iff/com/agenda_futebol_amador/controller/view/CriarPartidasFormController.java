package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.ICriarPartidaFormController;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.ICriarPartidaFormService;

public class CriarPartidasFormController implements ICriarPartidaFormController {

    @Autowired
    private ICriarPartidaFormService criarPartidaFormService;
    
    @Override
    public String handle(Model model) {
        criarPartidaFormService.prepararFormulario(model);
        return "partidas/form";
    }
}