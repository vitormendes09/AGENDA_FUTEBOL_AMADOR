package br.edu.iff.com.agenda_futebol_amador.services;

import br.edu.iff.com.agenda_futebol_amador.entities.AdministradorEntity;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import br.edu.iff.com.agenda_futebol_amador.repository.AdministradorRepository;
import br.edu.iff.com.agenda_futebol_amador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<AdministradorEntity> findAll() {
        return administradorRepository.findAll();
    }
    
    public Optional<AdministradorEntity> findById(Long id) {
        return administradorRepository.findById(id);
    }
    
    public AdministradorEntity save(AdministradorEntity administrador) {
        return administradorRepository.save(administrador);
    }
    
    public void deleteById(Long id) {
        administradorRepository.deleteById(id);
    }
    
    public List<UsuarioEntity> listAllUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public void removeUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    
    public AdministradorEntity createFromUsuario(Long usuarioId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        AdministradorEntity admin = new AdministradorEntity();
        admin.setNome(usuario.getNome());
        admin.setEmail(usuario.getEmail());
        admin.setSenha(usuario.getSenha());
        
        return administradorRepository.save(admin);
    }
    
    public long countTotalUsuarios() {
        return usuarioRepository.count();
    }
    
    public long countTotalPartidas() {
        return 0; // Implementar quando PartidaRepository estiver pronto
    }
}