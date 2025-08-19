package br.edu.iff.com.agenda_futebol_amador.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.ICriarPartidaService;

@Service
public class CriarPartidas implements ICriarPartidaService{
    
    @Override
    public IPartida perform(
        String nome, 
        String data, 
        String hora, 
        double valor, 
        String status, 
        int numeroJogadores, 
        IJogador organizador
    ) throws IllegalArgumentException {
        
        // Primeiro validamos os dados
        validarDadosPartida(nome, data, hora, valor, numeroJogadores);
        
        // TODO: Substituir por factory ou builder quando implementar as entidades concretas
        IPartida partida = new IPartida() {
            // Implementação mock da interface - será substituída pela entidade concreta
            private Long id;
            private String nomePartida;
            private String dataPartida;
            private String horaPartida;
            private String cidade = "São Fidélis"; // Default
            private double valorPartida;
            private int maxJogadores;
            private String statusPartida;
            private IJogador organizadorPartida;
            private List<IJogador> jogadores = new ArrayList<>();

            // Implementação dos métodos da interface
            @Override public Long getId() { return id; }
            @Override public String getNome() { return nomePartida; }
            @Override public String getData() { return dataPartida; }
            @Override public String getHora() { return horaPartida; }
            @Override public String getCidade() { return cidade; }
            @Override public double getValor() { return valorPartida; }
            @Override public int getNumeroJogadores() { return maxJogadores; }
            @Override public String getStatus() { return statusPartida; }
            @Override public IJogador getOrganizador() { return organizadorPartida; }
            @Override public List<IJogador> getJogadores() { return jogadores; }
            
            // Setters
            @Override public void setNome(String nome) { this.nomePartida = nome; }
            @Override public void setData(String data) { this.dataPartida = data; }
            @Override public void setHora(String hora) { this.horaPartida = hora; }
            @Override public void setCidade(String cidade) { this.cidade = cidade; }
            @Override public void setValor(double valor) { this.valorPartida = valor; }
            @Override public void setNumeroJogadores(int numero) { this.maxJogadores = numero; }
            @Override public void setStatus(String status) { this.statusPartida = status; }
            
            @Override 
            public void adicionarJogador(IJogador jogador) {
                if (jogadores.size() < maxJogadores) {
                    jogadores.add(jogador);
                }
            }
            
            @Override 
            public void removerJogador(IJogador jogador) {
                jogadores.remove(jogador);
            }
        };
        
        // Configura os valores da partida
        partida.setNome(nome);
        partida.setData(data);
        partida.setHora(hora);
        partida.setValor(valor);
        partida.setNumeroJogadores(numeroJogadores);
        partida.setStatus(status);
        partida.getJogadores().add(organizador); // Organizador também é jogador
        
        // TODO: Adicionar lógica de persistência quando o repositório estiver disponível
        
        return partida;
    }

    @Override
    public void validarDadosPartida(
        String nome, 
        String data, 
        String hora, 
        double valor, 
        int numeroJogadores
    ) throws IllegalArgumentException {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da partida não pode ser vazio");
        }
        
        if (data == null || !data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Data deve estar no formato DD/MM/AAAA");
        }
        
        if (hora == null || !hora.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Hora deve estar no formato HH:MM");
        }
        
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        
        if (numeroJogadores < 2) {
            throw new IllegalArgumentException("Número de jogadores deve ser pelo menos 2");
        }
    }
}
