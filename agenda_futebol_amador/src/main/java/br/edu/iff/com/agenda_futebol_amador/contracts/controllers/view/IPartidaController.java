package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPartidaController {
    @GetMapping("/partidas")
    String listarPartidas(Model model, @RequestParam(required = false) String cidade);
    
    @GetMapping("/partidas/{id}")
    String visualizarPartida(@PathVariable Long id, Model model);
    
    @GetMapping("/partidas/criar")
    String criarPartidaForm(Model model);
    
    @PostMapping("/partidas/criar")
    String criarPartidaSubmit(
            @RequestParam String nome,
            @RequestParam String data,
            @RequestParam String hora,
            @RequestParam String cidade,
            @RequestParam double valor,
            @RequestParam int numeroJogadores,
            @RequestParam String status);
    
    @GetMapping("/partidas/{id}/inscrever")
    String inscreverPartidaForm(@PathVariable Long id, Model model);
}