package br.edu.iff.com.agenda_futebol_amador.entities;
import java.util.List;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IAdministrador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

public class AdministradorEntity implements IAdministrador {
      private IUsuario usuario; 

    public AdministradorEntity(IUsuario usuario) {
        if(!"ADMINISTRADOR".equals(usuario.getTipo())) {
            throw new IllegalArgumentException("Tipo de usuário deve ser ADMINISTRADOR");
        }
        this.usuario = usuario;
    }

      // Implementação dos métodos específicos de Administrador
     @Override
    public List<IUsuario> listarTodosUsuarios() {
        // Implementação será feita no Service
        return null;
    }

    @Override
    public void removerUsuario(IUsuario usuario) {
        // Implementação será feita no Service
    }

    // Delegando os métodos de Usuario para o objeto usuario
    @Override
    public Long getId() { return usuario.getId(); }
    @Override
    public String getNome() { return usuario.getNome(); }
    @Override
    public String getEmail() { return usuario.getEmail(); }
    @Override
    public String getSenha() { return usuario.getSenha(); }
    @Override
    public String getTipo() { return usuario.getTipo(); }
    @Override
    public void setNome(String nome) { usuario.setNome(nome); }
    @Override
    public void setEmail(String email) { usuario.setEmail(email); }
    @Override
    public void setSenha(String senha) { usuario.setSenha(senha); }
   
}
