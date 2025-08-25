package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPartidaController {
    @GetMapping("/partidas")
    String listarPartidas(Model model);
    
    @GetMapping("/partidas/{id}")
    String visualizarPartida(@PathVariable Long id, Model model);
    
    @GetMapping("/partidas/criar")
    String criarPartidaForm(Model model);
    
    @GetMapping("/partidas/{id}/inscrever")
    String inscreverPartidaForm(@PathVariable Long id, Model model);
}