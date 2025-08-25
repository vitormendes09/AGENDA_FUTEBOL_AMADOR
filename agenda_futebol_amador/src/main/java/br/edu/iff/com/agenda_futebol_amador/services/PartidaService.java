package br.edu.iff.com.agenda_futebol_amador.services;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IJogadorService;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IPartidaService;
import br.edu.iff.com.agenda_futebol_amador.entities.PartidaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PartidaService implements IPartidaService {

    private final List<IPartida> partidas = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private final IJogadorService jogadorService;

    // Use @Lazy para quebrar a dependência circular
    public PartidaService(@Lazy IJogadorService jogadorService) {
        this.jogadorService = jogadorService;
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Buscar um jogador existente para ser organizador
        Optional<IJogador> organizadorOpt = jogadorService.findAll().stream().findFirst();
        
        if (organizadorOpt.isPresent()) {
            IPartida partida1 = new PartidaEntity(
                idCounter.getAndIncrement(),
                "Pelada do Bairro",
                "15/08/2024",
                "19:00",
                "São Fidélis",
                15.0,
                10,
                "PUBLICA",
                organizadorOpt.get()
            );
            
            partidas.add(partida1);
        }
    }

    @Override
    public List<IPartida> findAll() {
        return new ArrayList<>(partidas);
    }

    @Override
    public Optional<IPartida> findById(Long id) {
        return partidas.stream()
                .filter(partida -> partida.getId().equals(id))
                .findFirst();
    }

    @Override
    public IPartida save(IPartida partida) {
        if (partida.getId() == null) {
            if (partida instanceof PartidaEntity) {
                ((PartidaEntity) partida).setId(idCounter.getAndIncrement());
            }
            partidas.add(partida);
            return partida;
        } else {
            return findById(partida.getId())
                    .map(existing -> {
                        existing.setNome(partida.getNome());
                        existing.setData(partida.getData());
                        existing.setHora(partida.getHora());
                        existing.setCidade(partida.getCidade());
                        existing.setValor(partida.getValor());
                        existing.setNumeroJogadores(partida.getNumeroJogadores());
                        existing.setStatus(partida.getStatus());
                        return existing;
                    })
                    .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        }
    }

    @Override
    public void deleteById(Long id) {
        partidas.removeIf(partida -> partida.getId().equals(id));
    }

    @Override
    public List<IPartida> findByCidade(String cidade) {
        return partidas.stream()
                .filter(partida -> partida.getCidade().equalsIgnoreCase(cidade))
                .collect(Collectors.toList());
    }

    @Override
    public List<IPartida> findByOrganizador(Long organizadorId) {
        return partidas.stream()
                .filter(partida -> partida.getOrganizador() != null && 
                                  partida.getOrganizador().getId().equals(organizadorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<IPartida> findPartidasDisponiveis() {
        return partidas.stream()
                .filter(partida -> partida.getJogadores().size() < partida.getNumeroJogadores())
                .collect(Collectors.toList());
    }

    @Override
    public List<IPartida> findPartidasPublicas() {
        return partidas.stream()
                .filter(partida -> "PUBLICA".equals(partida.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void adicionarJogador(Long partidaId, Long jogadorId) {
        IPartida partida = findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        IJogador jogador = jogadorService.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        if (partida.getJogadores().size() >= partida.getNumeroJogadores()) {
            throw new IllegalStateException("Partida lotada");
        }
        
        partida.adicionarJogador(jogador);
    }

    @Override
    public void removerJogador(Long partidaId, Long jogadorId) {
        IPartida partida = findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        
        IJogador jogador = jogadorService.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        partida.removerJogador(jogador);
    }

    @Override
    public boolean isJogadorInscrito(Long partidaId, Long jogadorId) {
        return findById(partidaId)
                .map(partida -> partida.getJogadores().stream()
                        .anyMatch(jogador -> jogador.getId().equals(jogadorId)))
                .orElse(false);
    }

    @Override
    public int getVagasDisponiveis(Long partidaId) {
        return findById(partidaId)
                .map(partida -> partida.getNumeroJogadores() - partida.getJogadores().size())
                .orElse(0);
    }
}