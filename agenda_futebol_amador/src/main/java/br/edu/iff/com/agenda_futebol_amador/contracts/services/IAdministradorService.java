package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IAdministrador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import java.util.List;

public interface IAdministradorService extends IBaseService<IAdministrador> {
    List<IUsuario> listAllUsuarios();
    void removeUsuario(Long usuarioId);
    IAdministrador createFromUsuario(Long usuarioId);
    long countTotalUsuarios();
    long countTotalPartidas();
}