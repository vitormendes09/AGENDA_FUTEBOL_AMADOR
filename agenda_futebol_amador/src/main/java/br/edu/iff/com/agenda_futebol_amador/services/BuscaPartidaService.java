package br.edu.iff.com.agenda_futebol_amador.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IBuscaPartidaService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscaPartidaService implements IBuscaPartidaService {

    // Mock de dados - será substituído quando o repositório estiver implementado
    private final List<IPartida> partidasMock = criarPartidasMock();
    
    @Override
    public List<IPartida> buscarPorTermo(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodasPartidas();
        }
        
        String termoLower = termo.toLowerCase();
        return partidasMock.stream()
            .filter(p -> p.getNome().toLowerCase().contains(termoLower) ||
                         p.getCidade().toLowerCase().contains(termoLower) ||
                         p.getData().contains(termo) ||
                         p.getHora().contains(termo))
            .collect(Collectors.toList());
    }

    @Override
    public List<IPartida> listarTodasPartidas() {
        return Collections.unmodifiableList(partidasMock);
    }

    
    
    private List<IPartida> criarPartidasMock() {
        List<IPartida> mock = new ArrayList<>();
        
        // Adiciona partidas mockadas
        mock.add(criarPartidaMock(1L, "Pelada do Bairro", "15/08/2024", "19:00", "São Fidélis", 10, 15.0, "Agendada"));
        mock.add(criarPartidaMock(2L, "Torneio Amador", "20/08/2024", "16:00", "Campos dos Goytacazes", 20, 25.0, "Confirmada"));
        mock.add(criarPartidaMock(3L, "Futebol de Sexta", "12/08/2024", "20:00", "São Paulo", 12, 10.0, "Agendada"));
        mock.add(criarPartidaMock(4L, "Pelada da Praia", "18/08/2024", "08:00", "Rio de Janeiro", 8, 0.0, "Cancelada"));
        mock.add(criarPartidaMock(5L, "Campeonato Varzeano", "25/08/2024", "14:00", "São Fidélis", 22, 30.0, "Confirmada"));
        
        return mock;
    }
    
    private IPartida criarPartidaMock(Long id, String nome, String data, String hora, 
                                    String cidade, int numeroJogadores, double valor, String status) {
        return new IPartida() {
            @Override public Long getId() { return id; }
            @Override public String getNome() { return nome; }
            @Override public String getData() { return data; }
            @Override public String getHora() { return hora; }
            @Override public String getCidade() { return cidade; }
            @Override public double getValor() { return valor; }
            @Override public int getNumeroJogadores() { return numeroJogadores; }
            @Override public String getStatus() { return status; }
            @Override public IJogador getOrganizador() { return null; }
            @Override public List<IJogador> getJogadores() { return Collections.emptyList(); }
            
            // Setters (implementação vazia para mock)
            @Override public void setNome(String nome) {}
            @Override public void setData(String data) {}
            @Override public void setHora(String hora) {}
            @Override public void setCidade(String cidade) {}
            @Override public void setValor(double valor) {}
            @Override public void setNumeroJogadores(int numeroJogadores) {}
            @Override public void setStatus(String status) {}
            @Override public void adicionarJogador(IJogador jogador) {}
            @Override public void removerJogador(IJogador jogador) {}
        };
    }

    @Override
    public void prepararModeloParaView(ch.qos.logback.core.model.Model model, List<IPartida> partidas,
            String termoBusca) {
        ((Model) model).addAttribute("partidas", partidas);
        ((Model) model).addAttribute("termoBusca", termoBusca);
    }
}