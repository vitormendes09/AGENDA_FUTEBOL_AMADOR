package br.edu.iff.com.agenda_futebol_amador.controller.api;


import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;
import br.edu.iff.com.agenda_futebol_amador.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class RestApiUsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public RestApiUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Criar usuário
     @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                return new ResponseEntity<>("Email é obrigatório", HttpStatus.BAD_REQUEST);
            }
            if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
                return new ResponseEntity<>("Senha deve ter pelo menos 6 caracteres", HttpStatus.BAD_REQUEST);
            }
            
            Usuario savedUsuario = usuarioService.save(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Listar todos os usuários
    @Operation(summary = "Listar todos os usuários", description = "Retorna todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar usuário por ID
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            Optional<Usuario> usuarioOpt = usuarioService.findById(id);
            if (usuarioOpt.isPresent()) {
                usuarioDetails.setId(id);
                Usuario updatedUsuario = usuarioService.save(usuarioDetails);
                return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar usuário por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Buscar usuários por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> getUsuariosByNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.findByNome(nome);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar usuários por posição
    @GetMapping("/posicao/{posicao}")
    public ResponseEntity<List<Usuario>> getUsuariosByPosicao(@PathVariable String posicao) {
        List<Usuario> usuarios = usuarioService.findByPosicao(posicao);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Autenticar usuário
    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginRequest loginRequest) {
        boolean autenticado = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
        if (autenticado) {
            Optional<Usuario> usuario = usuarioService.findByEmail(loginRequest.getEmail());
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email ou senha inválidos", HttpStatus.UNAUTHORIZED);
        }
    }

    // Atualizar perfil do jogador
    @PutMapping("/{id}/perfil")
    public ResponseEntity<?> atualizarPerfil(@PathVariable Long id, @RequestBody PerfilRequest perfilRequest) {
        try {
            Usuario usuario = usuarioService.atualizarPerfil(id, perfilRequest.getPosicao(), perfilRequest.getNivelHabilidade());
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Classes auxiliares para requests
    public static class LoginRequest {
        private String email;
        private String senha;

        // Getters e Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class PerfilRequest {
        private String posicao;
        private Integer nivelHabilidade;

        // Getters e Setters
        public String getPosicao() { return posicao; }
        public void setPosicao(String posicao) { this.posicao = posicao; }
        public Integer getNivelHabilidade() { return nivelHabilidade; }
        public void setNivelHabilidade(Integer nivelHabilidade) { this.nivelHabilidade = nivelHabilidade; }
    }
}