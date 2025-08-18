package br.edu.iff.com.agenda_futebol_amador.services;
import org.springframework.stereotype.Service;


import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.ILoginFormService;

@Service
public class LoginFormService implements ILoginFormService{
     @Override
    public IUsuario perform(String email, String senha) {
     
        if ("admin@email.com".equals(email) && "admin123".equals(senha)) {
            
            return null; 
        } else if ("jogador@email.com".equals(email) && "jogador123".equals(senha)) {
            
            return null; 
        }
        
        return null; 
    }

    @Override
    public boolean temPermissao(IUsuario usuario) {
        if (usuario == null) return false;
        
        
        return true; 
    }

    @Override
    public String redirecionarAposLogin(IUsuario usuario) {
        if (usuario == null) {
            return "redirect:/login?error";
        }
        
       
        switch (usuario.getTipo()) {
            case "ADMIN":
                return "redirect:/admin/dashboard";
            case "JOGADOR":
                return "redirect:/jogador/partidas";
            default:
                return "redirect:/";
        }
    }
}
