package br.edu.iff.com.agenda_futebol_amador.service;

import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        // Verificar se email já existe (para novos usuários)
        if (usuario.getId() == null && usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Usuario> findByPosicao(String posicao) {
        return usuarioRepository.findByPosicao(posicao);
    }

    public List<Usuario> findByPerfil(String perfil) {
        return usuarioRepository.findByPerfil(perfil);
    }

    public boolean autenticar(String email, String senha) {
        return findByEmail(email)
                .map(usuario -> usuario.getSenha().equals(senha) && usuario.getAtivo())
                .orElse(false);
    }

    public Usuario atualizarPerfil(Long id, String posicao, Integer nivelHabilidade) {
        Optional<Usuario> usuarioOpt = findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setPosicao(posicao);
            usuario.setNivelHabilidade(nivelHabilidade);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuário não encontrado: " + id);
    }
}