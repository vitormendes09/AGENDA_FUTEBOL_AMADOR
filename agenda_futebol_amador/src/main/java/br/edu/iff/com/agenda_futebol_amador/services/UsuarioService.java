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
            Long newId = idCounter.getAndIncrement();
            // Definir o ID independentemente do tipo de implementação
            if (usuario instanceof UsuarioEntity) {
                ((UsuarioEntity) usuario).setId(newId);
            } else {
                // Para outras implementações, usar reflexão ou outro método
                try {
                    usuario.getClass().getMethod("setId", Long.class).invoke(usuario, newId);
                } catch (Exception e) {
                    throw new RuntimeException("Não foi possível definir o ID para o usuário", e);
                }
            }
            usuarios.add(usuario);
            return usuario;
        } else {
            // Atualizar usuário existente
            Optional<IUsuario> existingUserOpt = findById(usuario.getId());
            if (existingUserOpt.isPresent()) {
                IUsuario existing = existingUserOpt.get();
                existing.setNome(usuario.getNome());
                existing.setEmail(usuario.getEmail());
                existing.setSenha(usuario.getSenha());
                return existing;
            } else {
                throw new RuntimeException("Usuário não encontrado para atualização");
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    
    @Override
    public IUsuario authenticate(String email, String senha) {
        return findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    @Override
    public UsuarioEntity registerUsuario(String nome, String email, String senha, String tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerUsuario'");
    }
}