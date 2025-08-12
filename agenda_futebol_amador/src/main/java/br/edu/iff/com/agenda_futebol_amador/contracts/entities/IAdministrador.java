// Interface específica para Administrador
package br.edu.iff.com.agenda_futebol_amador.contracts.entities;

import java.util.List;

public interface IAdministrador extends IUsuario {
    List<IUsuario> listarTodosUsuarios();
    void removerUsuario(IUsuario usuario);
}