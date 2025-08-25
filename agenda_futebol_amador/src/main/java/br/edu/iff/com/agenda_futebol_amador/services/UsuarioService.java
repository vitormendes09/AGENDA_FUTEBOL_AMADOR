package br.edu.iff.com.agenda_futebol_amador.services;

import org.springframework.stereotype.Service;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IUsuarioService;
import br.edu.iff.com.agenda_futebol_amador.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioService implements IUsuarioService {

    private final List<IUsuario> usuarios = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<IUsuario> findAll() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Optional<IUsuario> findById(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<IUsuario> findByEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarios.stream()
                .anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public IUsuario save(IUsuario usuario) {
        if (usuario.getId() == null) {
            // Novo usuário
            Long newId = idCounter.getAndIncrement();
            if (usuario instanceof UsuarioEntity) {
                ((UsuarioEntity) usuario).setId(newId);
            }
            usuarios.add(usuario);
            return usuario;
        } else {
            // Atualizar usuário existente
            return findById(usuario.getId())
                    .map(existing -> {
                        existing.setNome(usuario.getNome());
                        existing.setEmail(usuario.getEmail());
                        existing.setSenha(usuario.getSenha());
                        return existing;
                    })
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        }
    }

    @Override
    public void deleteById(Long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    @Override
    public IUsuario registerUsuario(String nome, String email, String senha, String tipo) {
        if (existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado");
        }

        IUsuario novoUsuario = new UsuarioEntity(
            idCounter.getAndIncrement(),
            nome,
            email,
            senha, // Em produção, isso deve ser criptografado
            tipo
        );

        return save(novoUsuario);
    }

    @Override
    public IUsuario authenticate(String email, String senha) {
        return findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha)) // Em produção, usar BCrypt
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}