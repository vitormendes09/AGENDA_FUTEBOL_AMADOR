package br.edu.iff.com.agenda_futebol_amador.services;
import org.springframework.stereotype.Service;

import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.ILoginSubmitService;

@Service
public class LoginSubmitService implements ILoginSubmitService {
    @Override
    public IUsuario perform(String email, String senha) {
        // 1. Validar credenciais primeiro
        validarCredenciais(email, senha);
        
        // 2. Lógica de autenticação será implementada posteriormente
        // Retornará null por enquanto, até a implementação real
        return null;
    }

    @Override
    public void validarCredenciais(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        
        // Validação básica de formato de email
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}
