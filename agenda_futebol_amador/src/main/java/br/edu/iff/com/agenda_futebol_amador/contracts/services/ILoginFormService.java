package br.edu.iff.com.agenda_futebol_amador.contracts.services;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

public interface ILoginFormService {
     /**
     * Autentica um usuário com base no email e senha fornecidos.
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return IUsuario autenticado ou null se as credenciais forem inválidas
     */
    IUsuario perform(String email, String senha);
    
    /**
     * Verifica se o usuário tem permissão para acessar o sistema.
     * @param usuario Usuário a ser verificado
     * @return true se o usuário tem permissão, false caso contrário
     */
    boolean temPermissao(IUsuario usuario);
    
    /**
     * Redireciona o usuário para a página apropriada após o login.
     * @param usuario Usuário autenticado
     * @return String com o caminho para redirecionamento
     */
    String redirecionarAposLogin(IUsuario usuario);
}
