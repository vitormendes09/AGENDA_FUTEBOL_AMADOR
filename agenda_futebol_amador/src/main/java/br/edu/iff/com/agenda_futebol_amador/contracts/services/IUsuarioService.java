package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;

import java.util.Optional;

public interface IUsuarioService extends IBaseService<IUsuario> {
    Optional<IUsuario> findByEmail(String email);
    boolean existsByEmail(String email);
    UsuarioEntity registerUsuario(String nome, String email, String senha, String tipo);
    IUsuario authenticate(String email, String senha);
} 