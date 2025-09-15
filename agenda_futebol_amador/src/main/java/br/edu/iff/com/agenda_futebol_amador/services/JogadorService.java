package br.edu.iff.com.agenda_futebol_amador.services;

import br.edu.iff.com.agenda_futebol_amador.entities.JogadorEntity;
import br.edu.iff.com.agenda_futebol_amador.entities.PartidaEntity;
import br.edu.iff.com.agenda_futebol_amador.repository.JogadorRepository;
import br.edu.iff.com.agenda_futebol_amador.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private PartidaRepository partidaRepository;
    
    public List<JogadorEntity> findAll() {
        return jogadorRepository.findAll();
    }
    
    public Optional<JogadorEntity> findById(Long id) {
        return jogadorRepository.findById(id);
    }
    
    public JogadorEntity save(JogadorEntity jogador) {
        return jogadorRepository.save(jogador);
    }
    
    public void deleteById(Long id) {
        jogadorRepository.deleteById(id);
    }
    
    public List<PartidaEntity> getPartidasInscritas(Long jogadorId) {
        JogadorEntity jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        return jogador.getPartidasInscritas();
    }
    
    @Transactional
    public void inscreverEmPartida(Long jogadorId, Long partidaId) {
        JogadorEntity jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        partida.adicionarJogador(jogador);
        partidaRepository.save(partida);
    }
    
    @Transactional
    public void cancelarInscricaoPartida(Long jogadorId, Long partidaId) {
        JogadorEntity jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        partida.removerJogador(jogador);
        partidaRepository.save(partida);
    }
    
    public JogadorEntity createFromUsuario(Long usuarioId) {
        // Implementação simplificada - em um caso real, você converteria UsuarioEntity para JogadorEntity
        JogadorEntity jogador = new JogadorEntity();
        // Configurar propriedades...
        return jogadorRepository.save(jogador);
    }
}