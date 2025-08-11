package br.edu.iff.com.agenda_futebol_amador.controller.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IBuscarPartidaController;

@Controller
public class BuscarPartidaController implements IBuscarPartidaController {
    
    // Dados mockados - substituir por repository depois
    private static final List<PartidaMock> TODAS_PARTIDAS = Arrays.asList(
        new PartidaMock("Pelada do Bairro", "15/08/2024", "19:00", "São Fidélis", 10, 15.0),
        new PartidaMock("Torneio Amador", "20/08/2024", "16:00", "Campos dos Goytacazes", 20, 25.0),
        new PartidaMock("Futebol de Sexta", "12/08/2024", "20:00", "São Paulo", 12, 10.0),
        new PartidaMock("Pelada da Praia", "18/08/2024", "08:00", "Rio de Janeiro", 8, 0.0),
        new PartidaMock("Campeonato Varzeano", "25/08/2024", "14:00", "São Fidélis", 22, 30.0)
    );

    @Override
    public String handle(String termo, Model model) {
        List<PartidaMock> partidasEncontradas;
        
        if (termo == null || termo.trim().isEmpty()) {
            partidasEncontradas = TODAS_PARTIDAS;
        } else {
            String termoLower = termo.toLowerCase();
            partidasEncontradas = TODAS_PARTIDAS.stream()
                .filter(p -> p.getNome().toLowerCase().contains(termoLower) ||
                             p.getCidade().toLowerCase().contains(termoLower) ||
                             p.getData().contains(termo))
                .collect(Collectors.toList());
        }
        
        model.addAttribute("partidas", partidasEncontradas);
        model.addAttribute("termoBusca", termo);
        return "partidas/lista";
    }
    
    // Classe interna para mock (pode ser substituída pela entidade real depois)
    private static class PartidaMock {
        private String nome;
        private String data;
        private String hora;
        private String cidade;
        private int numeroJogadores;
        private double valor;
        
        public PartidaMock(String nome, String data, String hora, String cidade, int numeroJogadores, double valor) {
            this.nome = nome;
            this.data = data;
            this.hora = hora;
            this.cidade = cidade;
            this.numeroJogadores = numeroJogadores;
            this.valor = valor;
        }

        // Getters
        public String getNome() { return nome; }
        public String getData() { return data; }
        public String getHora() { return hora; }
        public String getCidade() { return cidade; }
        public int getNumeroJogadores() { return numeroJogadores; }
        public double getValor() { return valor; }
    }
}