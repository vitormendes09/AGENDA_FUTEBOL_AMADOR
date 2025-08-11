package br.edu.iff.com.agenda_futebol_amador.controller.view;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view.IHomeController;

@Controller
public class HomeController implements IHomeController {

    @Override
    public String home(Model model) { 

        // Dados mockados - serão substituídos pelo banco de dados depois

        List<PartidaMock> partidas = Arrays.asList(
            new PartidaMock("Pelada do Bairro", "15/08/2024", "19:00","São Fidélis", 10, 15.0),
            new PartidaMock("Torneio Amador", "20/08/2024", "16:00","Campos dos Goytacazes" ,20, 25.0),
            new PartidaMock("Futebol de Sexta", "12/08/2024", "20:00","São Paulo", 12, 10.0)
        );
        
        model.addAttribute("partidas", partidas);
        return "index";
    }
    
    // Classe interna para mock
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
        public String getCidade() {return cidade;}
        public int getNumeroJogadores() { return numeroJogadores; }
        public double getValor() { return valor; }
    }
}