package br.edu.iff.com.agenda_futebol_amador.contracts.services;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;


public interface IRegisterSubmitService {

     /**
     * Processa o registro de um novo usuário
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @param tipoUsuario Tipo do usuário (Jogador/Administrador)
     * @return Usuário registrado
     * @throws IllegalArgumentException Se os dados forem inválidos
     */
    
    IUsuario perform(String nome, String email, String senha, String tipoUsuario) throws IllegalArgumentException;
}
