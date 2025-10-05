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
import java.util.stream.Collectors;

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


    @GetMapping("/editar/{id}")
    public String editarPartida(@PathVariable Long id, 
                               @RequestParam(required = false) String nome, 
                               Model model) {
        var partidaOpt = partidaService.findById(id);
        if (partidaOpt.isPresent()) {
            model.addAttribute("pageTitle", "Editar Partida");
            model.addAttribute("item", partidaOpt.get());
            model.addAttribute("userName", nome != null ? nome : "Administrador");
            return "editar-partida";
        }
        return "redirect:/partidas";
    }

    @GetMapping("/gerenciar/{id}")
    public String gerenciarPartida(@PathVariable Long id,
                                  @RequestParam(required = false) String nome,
                                  Model model) {
        var partidaOpt = partidaService.findById(id);
        if (partidaOpt.isPresent()) {
            Partida partida = partidaOpt.get();
            List<Usuario> todosUsuarios = usuarioService.findAll();
            List<Usuario> usuariosInscritos = partida.getParticipantes();
            List<Usuario> usuariosDisponiveis = todosUsuarios.stream()
                    .filter(usuario -> !usuariosInscritos.contains(usuario))
                    .collect(Collectors.toList());

            model.addAttribute("pageTitle", "Gerenciar Partida");
            model.addAttribute("partida", partida);
            model.addAttribute("usuariosInscritos", usuariosInscritos);
            model.addAttribute("usuariosDisponiveis", usuariosDisponiveis);
            model.addAttribute("userName", nome != null ? nome : "Administrador");
            return "gerenciar-partida";
        }
        return "redirect:/partidas";
    }

    @GetMapping("/{partidaId}/inscrever-usuario/{usuarioId}")
    public String inscreverUsuarioNaPartida(@PathVariable Long partidaId,
                                           @PathVariable Long usuarioId,
                                           @RequestParam(required = false) String nome) {
        boolean sucesso = partidaService.inscreverEmPartida(partidaId, usuarioId);
        return "redirect:/partidas/gerenciar/" + partidaId + "?nome=" + (nome != null ? nome : "Administrador");
    }

    @GetMapping("/{partidaId}/remover-usuario/{usuarioId}")
    public String removerUsuarioDaPartida(@PathVariable Long partidaId,
                                         @PathVariable Long usuarioId,
                                         @RequestParam(required = false) String nome) {
        boolean sucesso = partidaService.cancelarInscricao(partidaId, usuarioId);
        return "redirect:/partidas/gerenciar/" + partidaId + "?nome=" + (nome != null ? nome : "Administrador");
    }

    @GetMapping("/minhas-inscricoes")
    public String minhasInscricoes(@RequestParam(required = false) String nome, Model model) {
        // Mock - em sistema real, filtraria pelas partidas do usuário logado
        // Por enquanto, vamos mostrar todas as partidas onde o usuário mock está inscrito
        Long usuarioId = 1L; // Usuário mock
        List<Partida> todasPartidas = partidaService.findAll();
        List<Partida> partidasInscrito = todasPartidas.stream()
                .filter(partida -> partida.getParticipantes().stream()
                        .anyMatch(usuario -> usuario.getId().equals(usuarioId)))
                .collect(Collectors.toList());

        model.addAttribute("pageTitle", "Minhas Inscrições");
        model.addAttribute("partidas", partidasInscrito);
        model.addAttribute("userName", nome != null ? nome : "Jogador");
        return "minhas-inscricoes";
    }
}