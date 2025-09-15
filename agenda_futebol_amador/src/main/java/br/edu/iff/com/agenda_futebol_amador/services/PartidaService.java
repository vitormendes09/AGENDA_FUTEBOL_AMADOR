package br.edu.iff.com.agenda_futebol_amador.services;

import br.edu.iff.com.agenda_futebol_amador.dto.PartidaDTO;
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
public class PartidaService {
    
    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    public List<PartidaEntity> findAll() {
        return partidaRepository.findAll();
    }
    
    public Optional<PartidaEntity> findById(Long id) {
        return partidaRepository.findById(id);
    }
    
    public List<PartidaEntity> findByCidade(String cidade) {
        return partidaRepository.findByCidade(cidade);
    }
    
    public List<PartidaEntity> findByOrganizador(Long organizadorId) {
        return partidaRepository.findByOrganizadorId(organizadorId);
    }
    
    public List<PartidaEntity> findPartidasDisponiveis() {
        return partidaRepository.findPartidasDisponiveis();
    }
    
    public List<PartidaEntity> findPartidasPublicas() {
        return partidaRepository.findPartidasPublicasDisponiveis();
    }
    
    @Transactional
    public PartidaEntity criarPartida(PartidaDTO partidaDTO) {
        JogadorEntity organizador = jogadorRepository.findById(partidaDTO.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));
        
        PartidaEntity partida = new PartidaEntity();
        partida.setNome(partidaDTO.getNome());
        partida.setData(partidaDTO.getData());
        partida.setHora(partidaDTO.getHora());
        partida.setCidade(partidaDTO.getCidade());
        partida.setValor(partidaDTO.getValor());
        partida.setNumeroJogadores(partidaDTO.getNumeroJogadores());
        partida.setStatus(partidaDTO.getStatus());
        partida.setOrganizador(organizador);
        
        return partidaRepository.save(partida);
    }
    
    @Transactional
    public void adicionarJogador(Long partidaId, Long jogadorId) {
        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        JogadorEntity jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        if (partida.getJogadores().size() >= partida.getNumeroJogadores()) {
            throw new IllegalStateException("Partida lotada");
        }
        
        partida.adicionarJogador(jogador);
        partidaRepository.save(partida);
    }
    
    @Transactional
    public void removerJogador(Long partidaId, Long jogadorId) {
        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        JogadorEntity jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        partida.removerJogador(jogador);
        partidaRepository.save(partida);
    }
    
    public boolean isJogadorInscrito(Long partidaId, Long jogadorId) {
        return partidaRepository.findById(partidaId)
                .map(partida -> partida.getJogadores().stream()
                        .anyMatch(jogador -> jogador.getId().equals(jogadorId)))
                .orElse(false);
    }
    
    public int getVagasDisponiveis(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .map(partida -> partida.getNumeroJogadores() - partida.getJogadores().size())
                .orElse(0);
    }
}