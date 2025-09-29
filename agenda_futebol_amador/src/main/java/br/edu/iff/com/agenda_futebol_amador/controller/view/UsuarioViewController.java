package br.edu.iff.com.agenda_futebol_amador.controller.view;


import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listUsuarios(@RequestParam(required = false) String nome, Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("pageTitle", "Usuários");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("item", new Usuario());
        model.addAttribute("userName", nome != null ? nome : "Administrador");
        return "usuarios";
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(@RequestParam(required = false) String nome, Model model) {
        model.addAttribute("pageTitle", "Cadastro de Usuário");
        model.addAttribute("item", new Usuario());
        model.addAttribute("userName", nome != null ? nome : "Visitante");
        return "cadastro-usuario";
    }

    @PostMapping("/save")
    public String saveUsuario(@Validated @ModelAttribute("item") Usuario usuario, 
                             BindingResult result, 
                             @RequestParam(required = false) String nome, 
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userName", nome != null ? nome : "Visitante");
            return "cadastro-usuario";
        }
        
        try {
            usuarioService.save(usuario);
            return "redirect:/usuarios?nome=" + (nome != null ? nome : "Usuário");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("userName", nome != null ? nome : "Visitante");
            return "cadastro-usuario";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, 
                               @RequestParam(required = false) String nome, 
                               Model model) {
        var usuarioOpt = usuarioService.findById(id);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("pageTitle", "Editar Usuário");
            model.addAttribute("item", usuarioOpt.get());
            model.addAttribute("userName", nome != null ? nome : "Administrador");
            return "editar-usuario";
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable Long id, @RequestParam(required = false) String nome) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios?nome=" + (nome != null ? nome : "Administrador");
    }

    @GetMapping("/perfil")
    public String verPerfil(@RequestParam(required = false) String nome, Model model) {
        // Mock - em sistema real, seria o usuário logado
        var usuarioOpt = usuarioService.findById(1L);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("pageTitle", "Meu Perfil");
            model.addAttribute("usuario", usuarioOpt.get());
            model.addAttribute("userName", nome != null ? nome : "Jogador");
            return "perfil-usuario";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "login";
    }
}