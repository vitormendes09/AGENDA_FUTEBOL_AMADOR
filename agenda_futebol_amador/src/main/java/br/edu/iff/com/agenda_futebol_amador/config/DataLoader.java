package br.edu.iff.com.agenda_futebol_amador.config;

import br.edu.iff.com.agenda_futebol_amador.entities.Partida;
import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.repository.PartidaRepository;
import br.edu.iff.com.agenda_futebol_amador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PartidaRepository partidaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar usuários iniciais
        Usuario usuario1 = new Usuario("Raphael Silva", "raphael@email.com", "123456", 
                "(22) 99999-1111", "Atacante", 4, "JOGADOR");
        
        Usuario usuario2 = new Usuario("João Pedro", "joao@email.com", "123456", 
                "(22) 99999-2222", "Meio-campo", 5, "ADMIN");
        
        Usuario usuario3 = new Usuario("Maria Santos", "maria@email.com", "123456", 
                "(22) 99999-3333", "Zagueira", 3, "JOGADOR");

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        usuarioRepository.save(usuario3);

        // Criar partidas iniciais
        Partida partida1 = new Partida("Futebol Sabado", 
            "Partida de futebol society no campo do centro", 
            LocalDateTime.now().plusDays(2), 
            "Campo Society Centro", 14, usuario1);
            
        Partida partida2 = new Partida("Volei Praia", 
            "Volei de praia no final de tarde", 
            LocalDateTime.now().plusDays(3), 
            "Praia do Futuro", 12, usuario2);

        partidaRepository.save(partida1);
        partidaRepository.save(partida2);

        System.out.println("Dados iniciais carregados com sucesso!");
    }
}