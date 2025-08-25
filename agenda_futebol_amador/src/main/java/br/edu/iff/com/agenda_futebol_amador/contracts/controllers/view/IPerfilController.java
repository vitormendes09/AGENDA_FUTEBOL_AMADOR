package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface IPerfilController {
    @GetMapping("/perfil")
    String visualizarPerfil(Model model);
    
    @GetMapping("/perfil/editar")
    String editarPerfilForm(Model model);
    
    @GetMapping("/perfil/partidas")
    String minhasPartidas(Model model);
}