package br.edu.iff.com.agenda_futebol_amador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class PartidaDTO {
    private Long id;
    
    @NotBlank(message = "Nome da partida é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    @NotNull(message = "Data é obrigatória")
    private LocalDate data;
    
    @NotNull(message = "Hora é obrigatória")
    private LocalTime hora;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String cidade;
    
    @PositiveOrZero(message = "Valor deve ser positivo ou zero")
    private double valor;
    
    @Positive(message = "Número de jogadores deve ser maior que zero")
    private int numeroJogadores;
    
    @NotBlank(message = "Status é obrigatório")
    private String status;
    
    private Long organizadorId;

    // Construtores
    public PartidaDTO() {}

    public PartidaDTO(String nome, LocalDate data, LocalTime hora, String cidade, 
                     double valor, int numeroJogadores, String status, Long organizadorId) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.cidade = cidade;
        this.valor = valor;
        this.numeroJogadores = numeroJogadores;
        this.status = status;
        this.organizadorId = organizadorId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public int getNumeroJogadores() { return numeroJogadores; }
    public void setNumeroJogadores(int numeroJogadores) { this.numeroJogadores = numeroJogadores; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getOrganizadorId() { return organizadorId; }
    public void setOrganizadorId(Long organizadorId) { this.organizadorId = organizadorId; }
}