package br.edu.iff.com.agenda_futebol_amador.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("JOGADOR")
public class JogadorEntity extends UsuarioEntity {

    @ManyToMany(mappedBy = "jogadores")
    private List<PartidaEntity> partidasInscritas = new ArrayList<>();

    // Construtores
    public JogadorEntity() {
        super();
    }

    public JogadorEntity(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Métodos específicos
    public List<PartidaEntity> getPartidasInscritas() { 
        return partidasInscritas; 
    }
    
    public void adicionarPartida(PartidaEntity partida) { 
        if (!partidasInscritas.contains(partida)) {
            partidasInscritas.add(partida);
            partida.getJogadores().add(this);
        }
    }
    
    public void removerPartida(PartidaEntity partida) { 
        partidasInscritas.remove(partida);
        partida.getJogadores().remove(this);
    }
}