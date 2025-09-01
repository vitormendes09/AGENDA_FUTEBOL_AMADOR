package br.edu.iff.com.agenda_futebol_amador.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

@Entity
@DiscriminatorValue("JOGADOR")
public class JogadorEntity extends UsuarioEntity {

    @ManyToMany
    @JoinTable(
        name = "jogador_partida",
        joinColumns = @JoinColumn(name = "jogador_id"),
        inverseJoinColumns = @JoinColumn(name = "partida_id")
    )
    private List<PartidaEntity> partidasInscritas = new ArrayList<>();

    // Construtores
    public JogadorEntity() {
        super();
    }

    public JogadorEntity(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Construtor que recebe IUsuario
    public JogadorEntity(IUsuario usuario) {
        super(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        this.setId(usuario.getId());
    }

    // Métodos específicos
    public List<PartidaEntity> getPartidasInscritas() { 
        return partidasInscritas; 
    }
    
    public void adicionarPartida(PartidaEntity partida) { 
        partidasInscritas.add(partida); 
    }
    
    public void removerPartida(PartidaEntity partida) { 
        partidasInscritas.remove(partida); 
    }
}