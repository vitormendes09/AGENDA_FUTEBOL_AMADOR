package br.edu.iff.com.agenda_futebol_amador.controller.view;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IPartidaController;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IJogadorService;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IPartidaService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PartidaController implements IPartidaController {

    private final IPartidaService partidaService;
    private final IJogadorService jogadorService; 

    public PartidaController(IPartidaService partidaService, IJogadorService jogadorService) {
        this.partidaService = partidaService;
        this.jogadorService = jogadorService;
    }

    @Override
    @GetMapping("/partidas")
    public String listarPartidas(Model model, @RequestParam(required = false) String cidade) {
        model.addAttribute("pageTitle", "Partidas Disponíveis - Peladas Amadoras");
        
        if (cidade != null && !cidade.isEmpty()) {
            model.addAttribute("partidas", partidaService.findByCidade(cidade));
        } else {
            model.addAttribute("partidas", partidaService.findAll());
        }
        
        return "partidas/listar";
    }

    @Override
    @GetMapping("/partidas/{id}")
    public String visualizarPartida(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Detalhes da Partida - Peladas Amadoras");
        partidaService.findById(id).ifPresent(partida -> {
            model.addAttribute("partida", partida);
            model.addAttribute("vagasDisponiveis", partidaService.getVagasDisponiveis(id));
        });
        return "partidas/detalhes";
    }

    @Override
    @GetMapping("/partidas/criar")
    public String criarPartidaForm(Model model) {
        model.addAttribute("pageTitle", "Criar Partida - Peladas Amadoras");
        return "partidas/criar";
    }

    @Override
    @PostMapping("/partidas/criar")
    public String criarPartidaSubmit(
            @RequestParam String nome,
            @RequestParam String data,
            @RequestParam String hora,
            @RequestParam String cidade,
            @RequestParam double valor,
            @RequestParam int numeroJogadores,
            @RequestParam String status,
            HttpSession session) { // Adicione HttpSession para obter o usuário logado
        
        // Obter o organizador logado (você precisa implementar a autenticação)
        // Por enquanto, vamos usar um organizador fixo para teste
        IJogador organizador = jogadorService.findById(1L) // Mude para obter da sessão
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));
        
        // Converter strings para LocalDate e LocalTime
        LocalDate dataPartida = LocalDate.parse(data);
        LocalTime horaPartida = LocalTime.parse(hora);
        
        // Criar e salvar a partida
        IPartida partida = partidaService.criarPartida(
            nome, dataPartida, horaPartida, cidade, 
            valor, numeroJogadores, status, organizador
        );
        
        return "redirect:/partidas?success";
    }

    @Override
    @GetMapping("/partidas/{id}/inscrever")
    public String inscreverPartidaForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Inscrever-se na Partida - Peladas Amadoras");
        model.addAttribute("partidaId", id);
        return "partidas/inscrever";
    }
}