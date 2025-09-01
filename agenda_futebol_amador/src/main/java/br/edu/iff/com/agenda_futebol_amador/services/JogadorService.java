package br.edu.iff.com.agenda_futebol_amador.services;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IJogadorService;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IPartidaService;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IUsuarioService;
import br.edu.iff.com.agenda_futebol_amador.entities.JogadorEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService implements IJogadorService {

    private final List<IJogador> jogadores = new ArrayList<>();
  
    private final IPartidaService partidaService;

    public JogadorService(IUsuarioService usuarioService, @Lazy IPartidaService partidaService) {
      
        this.partidaService = partidaService;
      
    }

    

    @Override
    public List<IJogador> findAll() {
        return new ArrayList<>(jogadores);
    }

    @Override
    public Optional<IJogador> findById(Long id) {
        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(id))
                .findFirst();
    }

    @Override
    public IJogador save(IJogador jogador) {
        if (findById(jogador.getId()).isEmpty()) {
            jogadores.add(jogador);
        }
        return jogador;
    }

    @Override
    public void deleteById(Long id) {
        jogadores.removeIf(jogador -> jogador.getId().equals(id));
    }

    @Override
    public List<IPartida> getPartidasInscritas(Long jogadorId) {
        return findById(jogadorId)
                .map(IJogador::getPartidasInscritas)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }

    @Override
    public void inscreverEmPartida(Long jogadorId, Long partidaId) {
        IJogador jogador = findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        partidaService.adicionarJogador(partidaId, jogadorId);
        jogador.adicionarPartida(partidaService.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada")));
    }

    @Override
    public void cancelarInscricaoPartida(Long jogadorId, Long partidaId) {
        IJogador jogador = findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        IPartida partida = partidaService.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        partidaService.removerJogador(partidaId, jogadorId);
        jogador.removerPartida(partida);
    }

    @Override
    public IJogador createFromUsuario(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFromUsuario'");
    }

  
}