package br.edu.iff.com.agenda_futebol_amador.components;

import br.edu.iff.com.agenda_futebol_amador.dto.PartidaCardDTO;
import br.edu.iff.com.agenda_futebol_amador.entities.PartidaEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    
    public PartidaCardDTO toPartidaCardDTO(PartidaEntity partida) {
        return new PartidaCardDTO(
            partida.getId(),
            partida.getNome(),
            partida.getData(),
            partida.getHora(),
            partida.getCidade(),
            partida.getValor(),
            partida.getNumeroJogadores(),
            partida.getJogadores().size(),
            partida.getStatus()
        );
    }
    
    public List<PartidaCardDTO> toPartidaCardDTOList(List<PartidaEntity> partidas) {
        return partidas.stream()
                .map(this::toPartidaCardDTO)
                .collect(Collectors.toList());
    }
}