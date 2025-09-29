package br.edu.iff.com.agenda_futebol_amador.controller.view;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.com.agenda_futebol_amador.entities.Partida;
import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.service.PartidaService;
import br.edu.iff.com.agenda_futebol_amador.service.UsuarioService;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequestMapping("/partidas")
public class PartidaViewController {

    private final PartidaService partidaService;
    private final UsuarioService usuarioService;

    public PartidaViewController(PartidaService partidaService, UsuarioService usuarioService) {
        this.partidaService = partidaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listPartidas(@RequestParam(required = false) String nome, Model model) {
        List<Partida> partidas = partidaService.findAll();
        model.addAttribute("pageTitle", "Partidas");
        model.addAttribute("partidas", partidas);
        model.addAttribute("item", new Partida());
        model.addAttribute("userName", nome != null ? nome : "Usuário");
        return "partidas";
    }

    @PostMapping("/save")
    public String savePartida(@Validated @ModelAttribute("item") Partida partida, 
                             BindingResult result, 
                             @RequestParam(required = false) String nome, 
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("partidas", partidaService.findAll());
            model.addAttribute("userName", nome != null ? nome : "Usuário");
            return "partidas";
        }
        
        // Define um criador mock (em um sistema real, seria o usuário logado)
        Usuario criador = usuarioService.findAll().get(0);
        partida.setCriador(criador);
        partida.setJogadoresAtuais(1);
        partida.getParticipantes().add(criador);
        
        partidaService.save(partida);
        return "redirect:/partidas?nome=" + (nome != null ? nome : "Usuário");
    }

    @GetMapping("/delete/{id}")
    public String deletePartida(@PathVariable Long id, @RequestParam(required = false) String nome) {
        partidaService.deleteById(id);
        return "redirect:/partidas?nome=" + (nome != null ? nome : "Usuário");
    }

    @GetMapping("/inscrever/{partidaId}")
    public String inscreverEmPartida(@PathVariable Long partidaId, 
                                   @RequestParam(required = false) String nome) {
        // Usuário mock (em sistema real, seria o usuário logado)
        Long usuarioId = 1L;
        partidaService.inscreverEmPartida(partidaId, usuarioId);
        return "redirect:/partidas?nome=" + (nome != null ? nome : "Usuário");
    }

    @GetMapping("/minhas-partidas")
    public String minhasPartidas(@RequestParam(required = false) String nome, Model model) {
        // Mock - em sistema real, filtraria pelas partidas do usuário logado
        List<Partida> partidas = partidaService.findAll();
        model.addAttribute("pageTitle", "Minhas Partidas");
        model.addAttribute("partidas", partidas);
        model.addAttribute("userName", nome != null ? nome : "Usuário");
        return "minhas-partidas";
    }
}