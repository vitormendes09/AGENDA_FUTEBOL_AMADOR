package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IPerfilController;

@Controller
public class PerfilController implements IPerfilController {

    @Override
    @GetMapping("/perfil")
    public String visualizarPerfil(Model model) {
        model.addAttribute("pageTitle", "Meu Perfil - Peladas Amadoras");
        return "perfil/visualizar";
    }

    @Override
    @GetMapping("/perfil/editar")
    public String editarPerfilForm(Model model) {
        model.addAttribute("pageTitle", "Editar Perfil - Peladas Amadoras");
        return "perfil/editar";
    }

    @Override
    @GetMapping("/perfil/partidas")
    public String minhasPartidas(Model model) {
        model.addAttribute("pageTitle", "Minhas Partidas - Peladas Amadoras");
        return "perfil/partidas";
    }
}