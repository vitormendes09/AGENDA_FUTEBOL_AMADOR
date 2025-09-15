package br.edu.iff.com.agenda_futebol_amador.repository;

import br.edu.iff.com.agenda_futebol_amador.entities.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Long> {
}