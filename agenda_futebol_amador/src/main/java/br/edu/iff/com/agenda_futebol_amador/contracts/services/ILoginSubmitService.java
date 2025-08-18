package br.edu.iff.com.agenda_futebol_amador.contracts.services;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;

public interface ILoginSubmitService {
    IUsuario perform(String email, String senha);
     void validarCredenciais(String email, String senha);
}
