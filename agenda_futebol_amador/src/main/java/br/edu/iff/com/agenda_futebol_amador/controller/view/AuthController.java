package br.edu.iff.com.agenda_futebol_amador.controller.view;

import br.edu.iff.com.agenda_futebol_amador.dto.LoginDTO;
import br.edu.iff.com.agenda_futebol_amador.dto.UsuarioDTO;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import br.edu.iff.com.agenda_futebol_amador.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("pageTitle", "Login - Peladas Amadoras");
        model.addAttribute("loginDTO", new LoginDTO());
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(LoginDTO loginDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            UsuarioEntity usuario = usuarioService.authenticate(loginDTO.getEmail(), loginDTO.getSenha());
            session.setAttribute("usuario", usuario);
            return "redirect:/";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("pageTitle", "Cadastro - Peladas Amadoras");
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String registerSubmit(UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registerUsuario(usuarioDTO);
            redirectAttributes.addFlashAttribute("success", "Usuário cadastrado com sucesso!");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}