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
    public long countTotalUsuarios() {
        return usuarioService.findAll().size();
    }

    @Override
    public long countTotalPartidas() {
        return 0; // Implementar quando PartidaService estiver pronto
    }

    @Override
    public IAdministrador createFromUsuario(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFromUsuario'");
    }
}