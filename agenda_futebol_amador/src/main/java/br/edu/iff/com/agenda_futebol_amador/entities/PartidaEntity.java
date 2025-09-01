package br.edu.iff.com.agenda_futebol_amador.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidas")
public class PartidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private LocalDate data;
    
    @Column(nullable = false)
    private LocalTime hora;
    
    @Column(nullable = false, length = 100)
    private String cidade;
    
    @Column(nullable = false)
    private double valor;
    
    @Column(nullable = false)
    private int numeroJogadores;
    
    @Column(nullable = false, length = 20)
    private String status; // "PRIVADA" ou "PUBLICA"
    
    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    private JogadorEntity organizador;
    
    @ManyToMany(mappedBy = "partidasInscritas")
    private List<JogadorEntity> jogadores = new ArrayList<>();

    // Construtores
    public PartidaEntity() {}

    public PartidaEntity(String nome, LocalDate data, LocalTime hora, String cidade, 
                        double valor, int numeroJogadores, String status, JogadorEntity organizador) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.cidade = cidade;
        this.valor = valor;
        this.numeroJogadores = numeroJogadores;
        this.status = status;
        this.organizador = organizador;
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
    
    public JogadorEntity getOrganizador() { return organizador; }
    public void setOrganizador(JogadorEntity organizador) { this.organizador = organizador; }
    
    public List<JogadorEntity> getJogadores() { return jogadores; }
    public void setJogadores(List<JogadorEntity> jogadores) { this.jogadores = jogadores; }

    // Métodos de negócio
    public void adicionarJogador(JogadorEntity jogador) {
        if(jogadores.size() >= numeroJogadores) {
            throw new IllegalStateException("Número máximo de jogadores atingido");
        }
        jogadores.add(jogador);
        jogador.getPartidasInscritas().add(this);
    }
    
    public void removerJogador(JogadorEntity jogador) {
        jogadores.remove(jogador);
        jogador.getPartidasInscritas().remove(this);
    }
}