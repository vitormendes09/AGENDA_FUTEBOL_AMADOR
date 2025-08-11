package br.edu.iff.com.agenda_futebol_amador.controller.view;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IListarPartidasController;

@Controller
public class ListarPartidaController implements IListarPartidasController {

    @Override
    public String handle(Model model) {
        // Lógica para listar partidas
        model.addAttribute("partidas");
        return "partidas/lista";
    }

   

    
}
