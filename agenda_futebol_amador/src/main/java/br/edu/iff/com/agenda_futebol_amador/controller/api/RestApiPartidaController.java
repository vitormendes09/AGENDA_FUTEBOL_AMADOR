package br.edu.iff.com.agenda_futebol_amador.controller.api;

import br.edu.iff.com.agenda_futebol_amador.entities.Partida;
import br.edu.iff.com.agenda_futebol_amador.service.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/partidas")
@Tag(name = "Partidas", description = "API para gerenciamento de partidas de futebol")
public class RestApiPartidaController {

    private final PartidaService partidaService;

    @Autowired
    public RestApiPartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @Operation(summary = "Criar nova partida", description = "Cria uma nova partida de futebol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Partida criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<Partida> createPartida(@RequestBody Partida partida) {
        if (partida.getTitulo() == null || partida.getTitulo().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Partida savedPartida = partidaService.save(partida);
        return new ResponseEntity<>(savedPartida, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as partidas", description = "Retorna todas as partidas cadastradas")
    @GetMapping
    public ResponseEntity<List<Partida>> getAllPartidas() {
        List<Partida> partidas = partidaService.findAll();
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }

    @Operation(summary = "Buscar partida por ID", description = "Retorna uma partida específica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Partida encontrada"),
        @ApiResponse(responseCode = "404", description = "Partida não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Partida> getPartidaById(
            @Parameter(description = "ID da partida") @PathVariable Long id) {
        Optional<Partida> partida = partidaService.findById(id);
        return partida.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualizar partida", description = "Atualiza os dados de uma partida existente")
    @PutMapping("/{id}")
    public ResponseEntity<Partida> updatePartida(
            @Parameter(description = "ID da partida") @PathVariable Long id, 
            @RequestBody Partida partidaDetails) {
        Optional<Partida> partidaOpt = partidaService.findById(id);
        if (partidaOpt.isPresent()) {
            partidaDetails.setId(id);
            Partida updatedPartida = partidaService.save(partidaDetails);
            return new ResponseEntity<>(updatedPartida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletar partida", description = "Remove uma partida do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartida(
            @Parameter(description = "ID da partida") @PathVariable Long id) {
        if (partidaService.findById(id).isPresent()) {
            partidaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Inscrever em partida", description = "Inscreve um usuário em uma partida")
    @PostMapping("/{partidaId}/inscrever/{usuarioId}")
    public ResponseEntity<Void> inscreverEmPartida(
            @Parameter(description = "ID da partida") @PathVariable Long partidaId,
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId) {
        boolean sucesso = partidaService.inscreverEmPartida(partidaId, usuarioId);
        if (sucesso) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Cancelar inscrição", description = "Cancela a inscrição de um usuário em uma partida")
    @PostMapping("/{partidaId}/cancelar-inscricao/{usuarioId}")
    public ResponseEntity<Void> cancelarInscricao(
            @Parameter(description = "ID da partida") @PathVariable Long partidaId,
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId) {
        boolean sucesso = partidaService.cancelarInscricao(partidaId, usuarioId);
        if (sucesso) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Listar partidas por status", description = "Retorna partidas filtradas por status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Partida>> getPartidasByStatus(
            @Parameter(description = "Status da partida") @PathVariable String status) {
        List<Partida> partidas = partidaService.findByStatus(status);
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }
}