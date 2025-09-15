package br.edu.iff.com.agenda_futebol_amador.controller.view;

import br.edu.iff.com.agenda_futebol_amador.components.Converter;
import br.edu.iff.com.agenda_futebol_amador.dto.PartidaCardDTO;
import br.edu.iff.com.agenda_futebol_amador.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private PartidaService partidaService;
    
    @Autowired
    private Converter converter;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Pelada Amadora - Home");
        
        // Buscar partidas públicas disponíveis
        List<PartidaCardDTO> partidas = converter.toPartidaCardDTOList(
            partidaService.findPartidasPublicas()
        );
        
        model.addAttribute("partidas", partidas);
        return "index";
    }
}