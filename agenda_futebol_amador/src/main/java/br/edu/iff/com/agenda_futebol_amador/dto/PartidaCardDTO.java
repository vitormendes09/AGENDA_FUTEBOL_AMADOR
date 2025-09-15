package br.edu.iff.com.agenda_futebol_amador.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PartidaCardDTO {
    private Long id;
    private String nome;
    private LocalDate data;
    private LocalTime hora;
    private String cidade;
    private double valor;
    private int numeroJogadores;
    private int jogadoresInscritos;
    private String status;

    public PartidaCardDTO(Long id, String nome, LocalDate data, LocalTime hora, 
                         String cidade, double valor, int numeroJogadores, 
                         int jogadoresInscritos, String status) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.cidade = cidade;
        this.valor = valor;
        this.numeroJogadores = numeroJogadores;
        this.jogadoresInscritos = jogadoresInscritos;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getData() { return data; }
    public LocalTime getHora() { return hora; }
    public String getCidade() { return cidade; }
    public double getValor() { return valor; }
    public int getNumeroJogadores() { return numeroJogadores; }
    public int getJogadoresInscritos() { return jogadoresInscritos; }
    public String getStatus() { return status; }
    public int getVagasDisponiveis() { return numeroJogadores - jogadoresInscritos; }
}