package br.edu.iff.com.agenda_futebol_amador.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class AdministradorEntity extends UsuarioEntity {

    public AdministradorEntity() {
        super();
        this.setTipo("ADMINISTRADOR");
    }

    public AdministradorEntity(String nome, String email, String senha) {
        super(nome, email, senha, "ADMINISTRADOR");
    }
}