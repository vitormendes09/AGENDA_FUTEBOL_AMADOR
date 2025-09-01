package br.edu.iff.com.agenda_futebol_amador.entities;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class AdministradorEntity extends UsuarioEntity {

    public AdministradorEntity() {
        super();
    }

    public AdministradorEntity(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Construtor que recebe IUsuario
    public AdministradorEntity(IUsuario usuario) {
        super(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        this.setId(usuario.getId());
    }
}