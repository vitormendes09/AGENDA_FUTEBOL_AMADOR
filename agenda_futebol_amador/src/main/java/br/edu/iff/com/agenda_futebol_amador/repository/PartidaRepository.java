package br.edu.iff.com.agenda_futebol_amador.repository;

import br.edu.iff.com.agenda_futebol_amador.entities.PartidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaEntity, Long> {
    List<PartidaEntity> findByCidade(String cidade);
    List<PartidaEntity> findByOrganizadorId(Long organizadorId);
    List<PartidaEntity> findByStatus(String status);
    
    @Query("SELECT p FROM PartidaEntity p WHERE SIZE(p.jogadores) < p.numeroJogadores")
    List<PartidaEntity> findPartidasDisponiveis();
    
    @Query("SELECT p FROM PartidaEntity p WHERE SIZE(p.jogadores) < p.numeroJogadores AND p.status = 'PUBLICA'")
    List<PartidaEntity> findPartidasPublicasDisponiveis();
}