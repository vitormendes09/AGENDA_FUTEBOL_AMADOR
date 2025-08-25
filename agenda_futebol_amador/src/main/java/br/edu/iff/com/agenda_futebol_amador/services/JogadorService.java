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
    private final IUsuarioService usuarioService;
    private final IPartidaService partidaService;

    // Use @Lazy para quebrar a dependência circular
    public JogadorService(IUsuarioService usuarioService, @Lazy IPartidaService partidaService) {
        this.usuarioService = usuarioService;
        this.partidaService = partidaService;
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Verificar se os usuários já existem antes de criar
        if (usuarioService.findByEmail("joao@email.com").isEmpty()) {
            IUsuario usuario1 = usuarioService.registerUsuario("João Silva", "joao@email.com", "senha123", "JOGADOR");
            IJogador jogador1 = createFromUsuario(usuario1.getId());
            jogadores.add(jogador1);
        }

        if (usuarioService.findByEmail("maria@email.com").isEmpty()) {
            IUsuario usuario2 = usuarioService.registerUsuario("Maria Santos", "maria@email.com", "senha123", "JOGADOR");
            IJogador jogador2 = createFromUsuario(usuario2.getId());
            jogadores.add(jogador2);
        }
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
        IUsuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!"JOGADOR".equals(usuario.getTipo())) {
            throw new RuntimeException("Usuário não é do tipo JOGADOR");
        }
        
        // Verificar se já existe um jogador para este usuário
        Optional<IJogador> existingJogador = jogadores.stream()
                .filter(jogador -> jogador.getId().equals(usuarioId))
                .findFirst();
        
        if (existingJogador.isPresent()) {
            return existingJogador.get();
        }
        
        IJogador jogador = new JogadorEntity(usuario);
        return save(jogador);
    }
}