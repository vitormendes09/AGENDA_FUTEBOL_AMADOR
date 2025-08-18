package br.edu.iff.com.agenda_futebol_amador.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IListarPartidasService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ListarPartidasService implements IListarPartidasService {

    // Mock de dados - será substituído quando o repositório estiver implementado
    private List<IPartida> partidasMock = criarPartidasMock();
    
    @Override
    public List<IPartida> listarTodasPartidas() {
        // TODO: Substituir por implementação real com repositório
        return Collections.unmodifiableList(partidasMock);
    }

    @Override
    public List<IPartida> listarPartidasPorStatus(String status) {
        // TODO: Substituir por implementação real com repositório
        List<IPartida> filtradas = new ArrayList<>();
        for (IPartida partida : partidasMock) {
            if (partida.getStatus().equalsIgnoreCase(status)) {
                filtradas.add(partida);
            }
        }
        return filtradas;
    }

    @Override
    public List<IPartida> listarPartidasPorJogador(IJogador jogador) {
        // TODO: Substituir por implementação real com repositório
        List<IPartida> partidasDoJogador = new ArrayList<>();
        for (IPartida partida : partidasMock) {
            if (partida.getJogadores().contains(jogador)) {
                partidasDoJogador.add(partida);
            }
        }
        return partidasDoJogador;
    }

    @Override
    public void prepararModeloParaView(Model model, List<IPartida> partidas) {
        model.addAttribute("partidas", partidas);
    }
    
    // Método auxiliar para criar dados mockados
    private List<IPartida> criarPartidasMock() {
        List<IPartida> mock = new ArrayList<>();
        
        // Partida 1
        mock.add(new IPartida() {
            @Override public Long getId() { return 1L; }
            @Override public String getNome() { return "Pelada Sexta-feira"; }
            @Override public String getData() { return "15/09/2023"; }
            @Override public String getHora() { return "19:00"; }
            @Override public String getCidade() { return "São Paulo"; }
            @Override public double getValor() { return 20.0; }
            @Override public int getNumeroJogadores() { return 10; }
            @Override public String getStatus() { return "Agendada"; }
            @Override public IJogador getOrganizador() { return null; } // Mock simplificado
            @Override public List<IJogador> getJogadores() { return Collections.emptyList(); }
            
            // Setters e outros métodos (implementação vazia para mock)
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
        
        // Partida 2
        mock.add(new IPartida() {
            @Override public Long getId() { return 2L; }
            @Override public String getNome() { return "Torneio de Verão"; }
            @Override public String getData() { return "22/09/2023"; }
            @Override public String getHora() { return "09:00"; }
            @Override public String getCidade() { return "Rio de Janeiro"; }
            @Override public double getValor() { return 50.0; }
            @Override public int getNumeroJogadores() { return 20; }
            @Override public String getStatus() { return "Confirmada"; }
            @Override public IJogador getOrganizador() { return null; }
            @Override public List<IJogador> getJogadores() { return Collections.emptyList(); }
            
            // Setters e outros métodos
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
        
        return mock;
    }
}