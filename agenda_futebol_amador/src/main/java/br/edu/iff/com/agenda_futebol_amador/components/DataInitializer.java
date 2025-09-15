package br.edu.iff.com.agenda_futebol_amador.components;

import br.edu.iff.com.agenda_futebol_amador.entities.AdministradorEntity;
import br.edu.iff.com.agenda_futebol_amador.entities.JogadorEntity;
import br.edu.iff.com.agenda_futebol_amador.repository.AdministradorRepository;
import br.edu.iff.com.agenda_futebol_amador.repository.JogadorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @PostConstruct
    public void init() {
        criarAdminPadrao();
        criarJogadoresTeste();
    }
    
    private void criarAdminPadrao() {
        if (administradorRepository.count() == 0) {
            AdministradorEntity admin = new AdministradorEntity();
            admin.setNome("Administrador");
            admin.setEmail("admin@peladas.com");
            admin.setSenha(new BCryptPasswordEncoder().encode("admin123"));
            administradorRepository.save(admin);
        }
    }
    
    private void criarJogadoresTeste() {
        if (jogadorRepository.count() == 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            JogadorEntity jogador1 = new JogadorEntity();
            jogador1.setNome("João Silva");
            jogador1.setEmail("joao@email.com");
            jogador1.setSenha(encoder.encode("senha123"));
            jogadorRepository.save(jogador1);
            
            JogadorEntity jogador2 = new JogadorEntity();
            jogador2.setNome("Maria Santos");
            jogador2.setEmail("maria@email.com");
            jogador2.setSenha(encoder.encode("senha123"));
            jogadorRepository.save(jogador2);
        }
    }
}