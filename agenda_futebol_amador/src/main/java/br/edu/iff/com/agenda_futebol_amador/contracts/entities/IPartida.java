package br.edu.iff.com.agenda_futebol_amador.contracts.entities;

import java.util.List;

public interface IPartida {
    Long getId();
    String getNome();
    String getData();
    String getHora();
    String getCidade();
    double getValor();
    int getNumeroJogadores();
    String getStatus();
    IJogador getOrganizador();
    List<IJogador> getJogadores();
    void setNome(String nome);
    void setData(String data);
    void setHora(String hora);
    void setCidade(String cidade);
    void setValor(double valor);
    void setNumeroJogadores(int numeroJogadores);
    void setStatus(String status);
    void adicionarJogador(IJogador jogador);
    void removerJogador(IJogador jogador);
}