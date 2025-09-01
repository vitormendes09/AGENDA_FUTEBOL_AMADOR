package br.edu.iff.com.agenda_futebol_amador.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jogadores")
@PrimaryKeyJoinColumn(name = "usuario_id")
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
        this.setTipo("JOGADOR");
    }

    public JogadorEntity(String nome, String email, String senha) {
        super(nome, email, senha, "JOGADOR");
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