package br.edu.iff.com.agenda_futebol_amador.entities;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import java.util.List;
import java.util.ArrayList;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;

public class PartidaEntity implements IPartida {
   
     private Long id;
    private String nome;
    private String data;
    private String hora;
    private String cidade;
    private double valor;
    private int numeroJogadores;
    private String status; // "PRIVADA" ou "PUBLICA"
    private IJogador organizador;
    private List<IJogador> jogadores;


    public PartidaEntity(Long id, String nome, String data, String hora, String cidade, 
                        double valor, int numeroJogadores, String status, IJogador organizador) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.cidade = cidade;
        this.valor = valor;
        this.numeroJogadores = numeroJogadores;
        this.status = status;
        this.organizador = organizador;
        this.jogadores = new ArrayList<>();
    }

     // Implementação dos métodos da interface Partida
    @Override
    public Long getId() { return id; }
    @Override
    public String getNome() { return nome; }
    @Override
    public String getData() { return data; }
    @Override
    public String getHora() { return hora; }
    @Override
    public String getCidade() { return cidade; }
    @Override
    public double getValor() { return valor; }
    @Override
    public int getNumeroJogadores() { return numeroJogadores; }
    @Override
    public String getStatus() { return status; }
    @Override
    public IJogador getOrganizador() { return organizador; }
    @Override
    public List<IJogador> getJogadores() { return jogadores; }
    
    @Override
    public void setNome(String nome) { this.nome = nome; }
    @Override
    public void setData(String data) { this.data = data; }
    @Override
    public void setHora(String hora) { this.hora = hora; }
    @Override
    public void setCidade(String cidade) { this.cidade = cidade; }
    @Override
    public void setValor(double valor) { this.valor = valor; }
    @Override
    public void setNumeroJogadores(int numeroJogadores) { this.numeroJogadores = numeroJogadores; }
    @Override
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public void adicionarJogador(IJogador jogador) {
        if(jogadores.size() >= numeroJogadores) {
            throw new IllegalStateException("Número máximo de jogadores atingido");
        }
        jogadores.add(jogador);
    }
    
    @Override
    public void removerJogador(IJogador jogador) {
        jogadores.remove(jogador);
    }
}
