package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import ch.qos.logback.core.model.Model;

import java.util.List;

public interface IListarPartidasService {
    /**
     * Lista todas as partidas disponíveis
     * @return Lista de partidas
     */
    List<IPartida> listarTodasPartidas();
    
    /**
     * Lista partidas por status
     * @param status Status para filtrar
     * @return Lista de partidas com o status especificado
     */
    List<IPartida> listarPartidasPorStatus(String status);
    
    /**
     * Lista partidas que um jogador específico está inscrito
     * @param jogador Jogador para filtrar
     * @return Lista de partidas do jogador
     */
    List<IPartida> listarPartidasPorJogador(IJogador jogador);
    
    /**
     * Prepara o modelo com as partidas para exibição na view
     * @param model Modelo Spring
     * @param partidas Lista de partidas a serem adicionadas
     */
    void prepararModeloParaView(org.springframework.ui.Model model, List<IPartida> partidas);
}