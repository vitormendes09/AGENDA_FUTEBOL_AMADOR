package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBuscaPartidaController {
    
    @GetMapping("/partidas/BuscarPartida")
    String handle(
            @RequestParam(required = false) String termo,
            Model model);
} 