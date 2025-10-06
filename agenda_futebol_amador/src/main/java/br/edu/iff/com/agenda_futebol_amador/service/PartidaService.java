package br.edu.iff.com.agenda_futebol_amador.service;

import br.edu.iff.com.agenda_futebol_amador.entities.Partida;
import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {
    
    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    public List<Partida> findAll() {
        return partidaRepository.findAll();
    }

    public Optional<Partida> findById(Long id) {
        return partidaRepository.findById(id);
    }

    public Partida save(Partida partida) {
        return partidaRepository.save(partida);
    }

    public void deleteById(Long id) {
        partidaRepository.deleteById(id);
    }

    public boolean inscreverEmPartida(Long partidaId, Long usuarioId) {
        Optional<Partida> partidaOpt = findById(partidaId);
        Optional<Usuario> usuarioOpt = usuarioService.findById(usuarioId);

        if (partidaOpt.isPresent() && usuarioOpt.isPresent()) {
            Partida partida = partidaOpt.get();
            Usuario usuario = usuarioOpt.get();
            
            if (partida.getJogadoresAtuais() < partida.getMaxJogadores() && 
                !partida.getParticipantes().contains(usuario)) {
                partida.adicionarParticipante(usuario);
                partidaRepository.save(partida);
                return true;
            }
        }
        return false;
    }

    public boolean cancelarInscricao(Long partidaId, Long usuarioId) {
        Optional<Partida> partidaOpt = findById(partidaId);
        Optional<Usuario> usuarioOpt = usuarioService.findById(usuarioId);

        if (partidaOpt.isPresent() && usuarioOpt.isPresent()) {
            Partida partida = partidaOpt.get();
            Usuario usuario = usuarioOpt.get();
            
            partida.removerParticipante(usuario);
            partidaRepository.save(partida);
            return true;
        }
        return false;
    }

    public List<Partida> findByStatus(String status) {
        return partidaRepository.findByStatus(status);
    }

    public List<Partida> findByTitulo(String titulo) {
        return partidaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Partida> findByCriador(Long criadorId) {
        return partidaRepository.findByCriadorId(criadorId);
    }
}