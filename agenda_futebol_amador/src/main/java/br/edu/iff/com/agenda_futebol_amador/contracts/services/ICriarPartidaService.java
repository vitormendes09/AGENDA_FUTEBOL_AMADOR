package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;

public interface ICriarPartidaService {
     /**
     * Cria uma nova partida com os dados fornecidos
     * @param nome Nome da partida
     * @param data Data da partida (formato DD/MM/YYYY)
     * @param hora Hora da partida (formato HH:MM)
     * @param valor Valor por jogador
     * @param status Status inicial da partida
     * @param numeroJogadores Número máximo de jogadores
     * @param organizador Jogador que está criando a partida
     * @return Partida criada
     * @throws IllegalArgumentException Se algum parâmetro for inválido
     */
    IPartida perform(
        String nome, 
        String data, 
        String hora, 
        double valor, 
        String status, 
        int numeroJogadores, 
        IJogador organizador
    ) throws IllegalArgumentException;
    
    /**
     * Valida os dados de criação da partida
     * @param nome Nome da partida
     * @param data Data da partida
     * @param hora Hora da partida
     * @param valor Valor por jogador
     * @param numeroJogadores Número máximo de jogadores
     * @throws IllegalArgumentException Se algum parâmetro for inválido
     */
    void validarDadosPartida(
        String nome, 
        String data, 
        String hora, 
        double valor, 
        int numeroJogadores
    ) throws IllegalArgumentException;
}
