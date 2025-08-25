package br.edu.iff.com.agenda_futebol_amador.entities;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

public class UsuarioEntity implements IUsuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;

    public UsuarioEntity(Long id, String nome, String email, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    @Override
    public Long getId() { return id; }
    
    // CORREÇÃO: Implementar corretamente o setId
    public void setId(Long id) { 
        this.id = id; 
    }
    
    @Override
    public String getNome() { return nome; }
    @Override
    public String getEmail() { return email; }
    @Override
    public String getSenha() { return senha; }
    @Override
    public String getTipo() { return tipo; }
    
    @Override
    public void setNome(String nome) { this.nome = nome; }
    @Override
    public void setEmail(String email) { this.email = email; }
    @Override
    public void setSenha(String senha) { this.senha = senha; }
}