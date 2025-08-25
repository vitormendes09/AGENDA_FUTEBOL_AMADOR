package br.edu.iff.com.agenda_futebol_amador.services;

import org.springframework.stereotype.Service;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IAdministrador;
import br.edu.iff.com.agenda_futebol_amador.contracts.entities.IUsuario;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IAdministradorService;
import br.edu.iff.com.agenda_futebol_amador.contracts.services.IUsuarioService;
import br.edu.iff.com.agenda_futebol_amador.entities.AdministradorEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService implements IAdministradorService {

    private final List<IAdministrador> administradores = new ArrayList<>();
    private final IUsuarioService usuarioService;

    public AdministradorService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        
        // Inicializar com admin padrão
        initializeAdminData();
    }

    private void initializeAdminData() {
        IUsuario adminUsuario = usuarioService.registerUsuario("Admin", "admin@email.com", "admin123", "ADMINISTRADOR");
        IAdministrador admin = createFromUsuario(adminUsuario.getId());
        administradores.add(admin);
    }

    @Override
    public List<IAdministrador> findAll() {
        return new ArrayList<>(administradores);
    }

    @Override
    public Optional<IAdministrador> findById(Long id) {
        return administradores.stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst();
    }

    @Override
    public IAdministrador save(IAdministrador administrador) {
        if (findById(administrador.getId()).isEmpty()) {
            administradores.add(administrador);
        }
        return administrador;
    }

    @Override
    public void deleteById(Long id) {
        administradores.removeIf(admin -> admin.getId().equals(id));
    }

    @Override
    public List<IUsuario> listAllUsuarios() {
        return usuarioService.findAll();
    }

    @Override
    public void removeUsuario(Long usuarioId) {
        usuarioService.deleteById(usuarioId);
    }

    @Override
    public IAdministrador createFromUsuario(Long usuarioId) {
        IUsuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!"ADMINISTRADOR".equals(usuario.getTipo())) {
            throw new RuntimeException("Usuário não é do tipo ADMINISTRADOR");
        }
        
        IAdministrador administrador = new AdministradorEntity(usuario);
        return save(administrador);
    }

    @Override
    public long countTotalUsuarios() {
        return usuarioService.findAll().size();
    }

    @Override
    public long countTotalPartidas() {
        // Este método será implementado quando o PartidaService estiver pronto
        return 0;
    }
}