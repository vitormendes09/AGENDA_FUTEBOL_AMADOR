package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAdminController {
    @GetMapping("/admin/usuarios")
    String gerenciarUsuarios(Model model);
    
    @GetMapping("/admin/partidas")
    String gerenciarPartidas(Model model);
    
    @GetMapping("/admin/estatisticas")
    String visualizarEstatisticas(Model model);
}