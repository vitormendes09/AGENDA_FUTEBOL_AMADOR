package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IPartidaController;

@Controller
public class PartidaController implements IPartidaController {

    @Override
    @GetMapping("/partidas")
    public String listarPartidas(Model model) {
        model.addAttribute("pageTitle", "Partidas Disponíveis - Peladas Amadoras");
        // A lista de partidas será adicionada via service
        return "partidas/listar";
    }

    @Override
    @GetMapping("/partidas/{id}")
    public String visualizarPartida(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Detalhes da Partida - Peladas Amadoras");
        model.addAttribute("partidaId", id);
        // Os dados da partida serão adicionados via service
        return "partidas/detalhes";
    }

    @Override
    @GetMapping("/partidas/criar")
    public String criarPartidaForm(Model model) {
        model.addAttribute("pageTitle", "Criar Partida - Peladas Amadoras");
        return "partidas/criar";
    }

    @Override
    @GetMapping("/partidas/{id}/inscrever")
    public String inscreverPartidaForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Inscrever-se na Partida - Peladas Amadoras");
        model.addAttribute("partidaId", id);
        return "partidas/inscrever";
    }
}