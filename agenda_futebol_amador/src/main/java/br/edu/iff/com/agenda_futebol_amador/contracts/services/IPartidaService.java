package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import java.util.List;

public interface IPartidaService extends IBaseService<IPartida> {
    List<IPartida> findByCidade(String cidade);
    List<IPartida> findByOrganizador(Long organizadorId);
    List<IPartida> findPartidasDisponiveis();
    List<IPartida> findPartidasPublicas();
    void adicionarJogador(Long partidaId, Long jogadorId);
    void removerJogador(Long partidaId, Long jogadorId);
    boolean isJogadorInscrito(Long partidaId, Long jogadorId);
    int getVagasDisponiveis(Long partidaId);
}