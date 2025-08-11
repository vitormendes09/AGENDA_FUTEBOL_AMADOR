package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface ICriarPartidaController {
    
    
    
    @PostMapping("/partidas/nova")
    String handle(
            @RequestParam String nome,
            @RequestParam String data,
            @RequestParam String hora,
            @RequestParam double valor,
            @RequestParam String status,
            @RequestParam int numeroJogadores,
            Model model);
        
}
