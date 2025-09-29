package br.edu.iff.com.agenda_futebol_amador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidas")
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode ser vazio.")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "A descrição não pode ser vazia.")
    @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres.")
    @Column(nullable = false, length = 500)
    private String descricao;

    @NotNull(message = "A data e hora não podem ser nulas.")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @NotBlank(message = "O local não pode ser vazio.")
    @Size(min = 3, max = 200, message = "O local deve ter entre 3 e 200 caracteres.")
    @Column(nullable = false, length = 200)
    private String local;

    @NotNull(message = "O número máximo de jogadores não pode ser nulo.")
    @Column(name = "max_jogadores", nullable = false)
    private Integer maxJogadores;

    @Column(name = "jogadores_atuais")
    private Integer jogadoresAtuais = 0;
    
    @Column(nullable = false, length = 20)
    private String status = "ABERTA"; // ABERTA, EM_ANDAMENTO, FINALIZADA, CANCELADA
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criador_id", nullable = false)
    private Usuario criador;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "partida_participantes",
        joinColumns = @JoinColumn(name = "partida_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> participantes = new ArrayList<>();

    public Partida() {}

    public Partida(String titulo, String descricao, LocalDateTime dataHora, 
                  String local, Integer maxJogadores, Usuario criador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.local = local;
        this.maxJogadores = maxJogadores;
        this.criador = criador;
        this.jogadoresAtuais = 1; // Começa com o criador
        this.participantes.add(criador);
    }

    // Getters e Setters (mantenha os mesmos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public Integer getMaxJogadores() { return maxJogadores; }
    public void setMaxJogadores(Integer maxJogadores) { this.maxJogadores = maxJogadores; }

    public Integer getJogadoresAtuais() { return jogadoresAtuais; }
    public void setJogadoresAtuais(Integer jogadoresAtuais) { this.jogadoresAtuais = jogadoresAtuais; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Usuario getCriador() { return criador; }
    public void setCriador(Usuario criador) { this.criador = criador; }

    public List<Usuario> getParticipantes() { return participantes; }
    public void setParticipantes(List<Usuario> participantes) { this.participantes = participantes; }

    public void adicionarParticipante(Usuario usuario) {
        if (this.participantes.size() < maxJogadores && !this.participantes.contains(usuario)) {
            this.participantes.add(usuario);
            this.jogadoresAtuais = this.participantes.size();
        }
    }

    public void removerParticipante(Usuario usuario) {
        if (this.participantes.remove(usuario)) {
            this.jogadoresAtuais = this.participantes.size();
        }
    }

    @PrePersist
    @PreUpdate
    private void atualizarJogadoresAtuais() {
        if (this.participantes != null) {
            this.jogadoresAtuais = this.participantes.size();
        } else {
            this.jogadoresAtuais = 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Partida other = (Partida) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}