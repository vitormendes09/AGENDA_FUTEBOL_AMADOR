package br.edu.iff.com.agenda_futebol_amador.contracts.services;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

public interface IRegisterFormService {
    /**
     * Registra um novo usuário no sistema
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @param tipo Tipo do usuário (JOGADOR ou ADMINISTRADOR)
     * @return Usuário registrado
     * @throws RegistroException Se ocorrer algum erro durante o registro
     */
    IUsuario perform (String nome, String email, String senha, String tipo) ;
}
