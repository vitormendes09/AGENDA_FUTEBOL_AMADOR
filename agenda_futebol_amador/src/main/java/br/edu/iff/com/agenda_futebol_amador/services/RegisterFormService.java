package br.edu.iff.com.agenda_futebol_amador.services;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IRegisterFormService;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RegisterFormService implements IRegisterFormService{
    private List<IUsuario> usuariosRegistrados = new ArrayList<>();
    private Long currentId = 1L;
    
    @Override
    public IUsuario perform (String nome, String email, String senha, String tipo) {
        
        
        IUsuario novoUsuario = new UsuarioEntity(currentId++, nome, email, senha, tipo);
        usuariosRegistrados.add(novoUsuario);
        
        return novoUsuario;
    }

   
}
