package br.edu.iff.com.agenda_futebol_amador.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.ICriarPartidaFormService;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;

@Service
public class CriarPartidaFormService implements ICriarPartidaFormService {

    @Override
    public void prepararFormulario(Model model) {
        // Adiciona opções de status disponíveis
        model.addAttribute("opcoesStatus", new String[]{"Agendada", "Confirmada", "Cancelada"});
        
        // Adiciona valores padrão
        adicionarValoresPadrao(model);
        
        // Adiciona flag de permissão
        model.addAttribute("podeCriar", usuarioPodeCriarPartidas());
    }

    @Override
    public void adicionarValoresPadrao(Model model) {
        // Valores iniciais para o formulário
        model.addAttribute("partida", new IPartida() {
            // Implementação mock similar à anterior, porém simplificada
            @Override public Long getId() { return null; }
            @Override public String getNome() { return ""; }
            @Override public String getData() { return ""; }
            @Override public String getHora() { return "19:00"; } // Hora padrão
            @Override public String getCidade() { return "São Paulo"; } // Cidade padrão
            @Override public double getValor() { return 20.0; } // Valor padrão
            @Override public int getNumeroJogadores() { return 10; } // Número padrão
            @Override public String getStatus() { return "Agendada"; } // Status padrão
            @Override public IJogador getOrganizador() { return null; }
            @Override public List<IJogador> getJogadores() { return Collections.emptyList(); }
            
            // Setters (implementação vazia pois é apenas para exibição)
            @Override public void setNome(String nome) {}
            @Override public void setData(String data) {}
            @Override public void setHora(String hora) {}
            @Override public void setCidade(String cidade) {}
            @Override public void setValor(double valor) {}
            @Override public void setNumeroJogadores(int numeroJogadores) {}
            @Override public void setStatus(String status) {}
            @Override public void adicionarJogador(IJogador jogador) {}
            @Override public void removerJogador(IJogador jogador) {}
        });
    }

    @Override
    public boolean usuarioPodeCriarPartidas() {
        // TODO: Implementar lógica real quando tiver o contexto de autenticação
        // Por enquanto, assumimos que qualquer jogador pode criar partidas
        // No futuro, pode verificar roles, permissões, etc.
        
        // Mock: retorna true para testes
        return true;
        
        // Implementação futura pode ser:
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // return authentication != null && 
        //        authentication.getAuthorities().stream()
        //            .anyMatch(a -> a.getAuthority().equals("JOGADOR"));
    }
}