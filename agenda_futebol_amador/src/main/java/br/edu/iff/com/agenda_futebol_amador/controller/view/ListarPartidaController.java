package br.edu.iff.com.agenda_futebol_amador.controller.view;

import java.util.List; // Corrigido o import para usar java.util.List
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IListarPartidasController;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IListarPartidasService;

@Controller
public class ListarPartidaController implements IListarPartidasController {

    @Autowired
    private IListarPartidasService listarPartidasService;
    
    @Override
    public String handle(Model model) {
        List<IPartida> partidas = listarPartidasService.listarTodasPartidas();
        listarPartidasService.prepararModeloParaView(model, partidas);
        return "partidas/lista";
    }
}