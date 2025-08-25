package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IJogador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IPartida;
import java.util.List;

public interface IJogadorService extends IBaseService<IJogador> {
    List<IPartida> getPartidasInscritas(Long jogadorId);
    void inscreverEmPartida(Long jogadorId, Long partidaId);
    void cancelarInscricaoPartida(Long jogadorId, Long partidaId);
    IJogador createFromUsuario(Long usuarioId);
}