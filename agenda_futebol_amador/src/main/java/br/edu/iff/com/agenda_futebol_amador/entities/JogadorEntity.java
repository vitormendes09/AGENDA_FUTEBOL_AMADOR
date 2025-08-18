package br.edu.iff.com.agenda_futebol_amador.entities;

import java.util.List;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import java.util.ArrayList;


public class JogadorEntity implements IJogador {

    private IUsuario usuario;
    
    private List<IPartida> partidasInscritas;
    
    public JogadorEntity(IUsuario usuario) {
        if(!"JOGADOR".equals(usuario.getTipo())) {
            throw new IllegalArgumentException("Tipo de usuário deve ser JOGADOR");
        }
        this.usuario = usuario;
        this.partidasInscritas = new ArrayList<>();
    }
     @Override
    public List<IPartida> getPartidasInscritas() { return partidasInscritas; }
    @Override
    public void adicionarPartida(IPartida partida) { partidasInscritas.add(partida); }
    @Override
    public void removerPartida(IPartida partida) { partidasInscritas.remove(partida); }

    // Delegando os métodos de Usuario para o objeto usuario
    @Override
    public Long getId() { return usuario.getId(); }
    @Override
    public String getNome() { return usuario.getNome(); }
    @Override
    public String getEmail() { return usuario.getEmail(); }
    @Override
    public String getSenha() { return usuario.getSenha(); }
    @Override
    public String getTipo() { return usuario.getTipo(); }
    @Override
    public void setNome(String nome) { usuario.setNome(nome); }
    @Override
    public void setEmail(String email) { usuario.setEmail(email); }
    @Override
    public void setSenha(String senha) { usuario.setSenha(senha); }
    
}
