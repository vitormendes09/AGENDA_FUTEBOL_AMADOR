package br.edu.iff.com.agenda_futebol_amador.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IAdminController;

@Controller
public class AdminController implements IAdminController {

    @Override
    @GetMapping("/admin/usuarios")
    public String gerenciarUsuarios(Model model) {
        model.addAttribute("pageTitle", "Gerenciar Usuários - Painel Admin");
        return "admin/usuarios";
    }

    @Override
    @GetMapping("/admin/partidas")
    public String gerenciarPartidas(Model model) {
        model.addAttribute("pageTitle", "Gerenciar Partidas - Painel Admin");
        return "admin/partidas";
    }

    @Override
    @GetMapping("/admin/estatisticas")
    public String visualizarEstatisticas(Model model) {
        model.addAttribute("pageTitle", "Estatísticas - Painel Admin");
        return "admin/estatisticas";
    }
}