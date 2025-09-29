package br.edu.iff.com.agenda_futebol_amador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.com.agenda_futebol_amador.entities.Partida;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByStatus(String status);
    List<Partida> findByTituloContainingIgnoreCase(String titulo);
    List<Partida> findByCriadorId(Long criadorId);
}