package br.edu.iff.com.agenda_futebol_amador.controller.view;

import br.edu.iff.com.agenda_futebol_amador.dto.PartidaDTO;
import br.edu.iff.com.agenda_futebol_amador.entities.PartidaEntity;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import br.edu.iff.com.agenda_futebol_amador.services.PartidaService;
import br.edu.iff.com.agenda_futebol_amador.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    
    @Autowired
    private PartidaService partidaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public String listarPartidas(Model model, @RequestParam(required = false) String cidade) {
        model.addAttribute("pageTitle", "Partidas Disponíveis - Peladas Amadoras");
        
        List<PartidaEntity> partidas;
        if (cidade != null && !cidade.isEmpty()) {
            partidas = partidaService.findByCidade(cidade);
        } else {
            partidas = partidaService.findPartidasDisponiveis();
        }
        
        model.addAttribute("partidas", partidas);
        return "partidas/listar";
    }
    
    @GetMapping("/{id}")
    public String visualizarPartida(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Detalhes da Partida - Peladas Amadoras");
        
        partidaService.findById(id).ifPresent(partida -> {
            model.addAttribute("partida", partida);
            model.addAttribute("vagasDisponiveis", partidaService.getVagasDisponiveis(id));
        });
        
        return "partidas/detalhes";
    }
    
    @GetMapping("/criar")
    public String criarPartidaForm(Model model, HttpSession session) {
        model.addAttribute("pageTitle", "Criar Partida - Peladas Amadoras");
        model.addAttribute("partidaDTO", new PartidaDTO());
        return "partidas/criar";
    }
    
    @PostMapping("/criar")
    public String criarPartidaSubmit(PartidaDTO partidaDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
            if (usuario == null) {
                throw new RuntimeException("Usuário não autenticado");
            }
            
            partidaDTO.setOrganizadorId(usuario.getId());
            partidaService.criarPartida(partidaDTO);
            redirectAttributes.addFlashAttribute("success", "Partida criada com sucesso!");
            return "redirect:/partidas";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/partidas/criar";
        }
    }
    
    @GetMapping("/{id}/inscrever")
    public String inscreverPartida(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
            if (usuario == null) {
                throw new RuntimeException("Usuário não autenticado");
            }
            
            partidaService.adicionarJogador(id, usuario.getId());
            redirectAttributes.addFlashAttribute("success", "Inscrição realizada com sucesso!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/partidas/" + id;
    }
}