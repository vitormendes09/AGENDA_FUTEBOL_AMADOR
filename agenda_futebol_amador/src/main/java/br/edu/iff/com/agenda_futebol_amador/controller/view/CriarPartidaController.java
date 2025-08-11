package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.ICriarPartidaController;

public class CriarPartidaController implements ICriarPartidaController{

     @Override
    public String handle(String nome, String data, String hora, 
                             double valor, String status, int numeroJogadores, 
                             Model model) {
        // Lógica de criação de partida
        model.addAttribute("mensagem", "Partida criada com sucesso!");
        return "redirect:/partidas";
    }
    
}
