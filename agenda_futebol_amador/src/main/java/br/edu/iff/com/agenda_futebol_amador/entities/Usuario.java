package br.edu.iff.com.agenda_futebol_amador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O email não pode ser vazio.")
    @Email(message = "Email deve ser válido.")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(nullable = false, length = 255)
    private String senha;

    @NotBlank(message = "O telefone não pode ser vazio.")
    @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres.")
    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(length = 50)
    private String posicao; // Ex: Goleiro, Zagueiro, Meio-campo, Atacante
    
    @Column(name = "nivel_habilidade")
    private Integer nivelHabilidade; // 1-5 (iniciante a avançado)
    
    @Column(nullable = false, length = 20)
    private String perfil = "JOGADOR"; // JOGADOR, ADMIN
    
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Partida> partidasCriadas = new ArrayList<>();
    
    @ManyToMany(mappedBy = "participantes", fetch = FetchType.LAZY)
    private List<Partida> partidasInscritas = new ArrayList<>();

    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Usuario(String nome, String email, String senha, String telefone, 
                   String posicao, Integer nivelHabilidade, String perfil) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.posicao = posicao;
        this.nivelHabilidade = nivelHabilidade;
        this.perfil = perfil != null ? perfil : "JOGADOR";
    }

    // Getters e Setters (mantenha os mesmos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public Integer getNivelHabilidade() { return nivelHabilidade; }
    public void setNivelHabilidade(Integer nivelHabilidade) { this.nivelHabilidade = nivelHabilidade; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<Partida> getPartidasCriadas() { return partidasCriadas; }
    public void setPartidasCriadas(List<Partida> partidasCriadas) { this.partidasCriadas = partidasCriadas; }

    public List<Partida> getPartidasInscritas() { return partidasInscritas; }
    public void setPartidasInscritas(List<Partida> partidasInscritas) { this.partidasInscritas = partidasInscritas; }

    // Métodos auxiliares
    public void adicionarPartidaCriada(Partida partida) {
        if (!this.partidasCriadas.contains(partida)) {
            this.partidasCriadas.add(partida);
            partida.setCriador(this);
        }
    }

    public void adicionarPartidaInscrita(Partida partida) {
        if (!this.partidasInscritas.contains(partida)) {
            this.partidasInscritas.add(partida);
        }
    }

    public void removerPartidaInscrita(Partida partida) {
        this.partidasInscritas.remove(partida);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}