// Interface específica para Jogador
package br.edu.iff.com.agenda_futebol_amador.contracts.entities;

import java.util.List;

public interface IJogador extends IUsuario {
    List<IPartida> getPartidasInscritas();
    void adicionarPartida(IPartida partida);
    void removerPartida(IPartida partida);
}