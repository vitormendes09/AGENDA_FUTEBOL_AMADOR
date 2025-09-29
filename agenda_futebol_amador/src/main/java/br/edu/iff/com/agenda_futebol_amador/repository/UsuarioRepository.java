package br.edu.iff.com.agenda_futebol_amador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.com.agenda_futebol_amador.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    List<Usuario> findByPosicao(String posicao);
    List<Usuario> findByPerfil(String perfil);
    List<Usuario> findByAtivoTrue();
    boolean existsByEmail(String email);
}