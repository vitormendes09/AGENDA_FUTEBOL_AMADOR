package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import ch.qos.logback.core.model.Model;

import java.util.List;

public interface IBuscaPartidaService {
    /**
     * Busca partidas por termo de pesquisa
     * @param termo Termo para busca (pode ser nome, cidade, data, etc.)
     * @return Lista de partidas encontradas
     */
    List<IPartida> buscarPorTermo(String termo);
    
    /**
     * Lista todas as partidas disponíveis
     * @return Lista completa de partidas
     */
    List<IPartida> listarTodasPartidas();
    
    /**
     * Prepara o modelo com os resultados da busca
     * @param model Modelo Spring
     * @param partidas Lista de partidas encontradas
     * @param termoBusca Termo utilizado na busca (pode ser nulo)
     */
    void prepararModeloParaView(Model model, List<IPartida> partidas, String termoBusca);
}