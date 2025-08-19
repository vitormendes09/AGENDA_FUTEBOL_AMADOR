// Interface base para usuário
package br.edu.iff.com.agenda_futebol_amador.contracts.entities;

public interface IUsuario {
    Long getId();
    String getNome();
    String getEmail();
    String getSenha();
    String getTipo();
    void setNome(String nome);
    void setEmail(String email);
    void setSenha(String senha);
}