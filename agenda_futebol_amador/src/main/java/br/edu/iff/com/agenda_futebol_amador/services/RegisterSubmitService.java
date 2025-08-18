package br.edu.iff.com.agenda_futebol_amador.services;
import org.springframework.stereotype.Service;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IRegisterSubmitService;
import br.edu.iff.com.agenda_futebol_amador.entities.AdministradorEntity;
import br.edu.iff.com.agenda_futebol_amador.entities.JogadorEntity;;

@Service
public class RegisterSubmitService implements IRegisterSubmitService{
    
  public IUsuario perform(String nome, String email, String senha, String tipoUsuario) throws IllegalArgumentException {
        // Validação básica dos parâmetros
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        if (tipoUsuario == null || (!tipoUsuario.equals("Jogador") && !tipoUsuario.equals("Administrador"))) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        // Criação do usuário conforme o tipo
        IUsuario usuario = null;
        if (tipoUsuario.equals("Jogador")) {
            usuario = new JogadorEntity(usuario);
        } else {
            usuario = new AdministradorEntity(usuario);
        }

        // Configuração dos atributos básicos
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha); // Em produção, isso deveria ser um hash da senha

        // Aqui normalmente teria a persistência no banco de dados
        // Como mencionado que não há repositórios ainda, apenas retornamos o usuário criado
        
        return usuario;
    }
}
