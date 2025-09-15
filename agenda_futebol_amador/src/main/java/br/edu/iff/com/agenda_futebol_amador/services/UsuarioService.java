package br.edu.iff.com.agenda_futebol_amador.services;

import br.edu.iff.com.agenda_futebol_amador.dto.UsuarioDTO;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import br.edu.iff.com.agenda_futebol_amador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }
    
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<UsuarioEntity> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public UsuarioEntity registerUsuario(UsuarioDTO usuarioDTO) {
        if (existsByEmail(usuarioDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha()); // Em produção, criptografar a senha
        
        return usuarioRepository.save(usuario);
    }
    
    public UsuarioEntity authenticate(String email, String senha) {
        return findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha)) // Em produção, usar BCrypt
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}