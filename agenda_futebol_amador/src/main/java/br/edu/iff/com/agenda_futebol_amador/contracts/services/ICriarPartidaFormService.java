package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import org.springframework.ui.Model;

public interface ICriarPartidaFormService {
     /**
     * Prepara o modelo com os dados necessários para exibir o formulário de criação de partidas
     * @param model Modelo Spring que será preenchido com os atributos necessários
     */
    void prepararFormulario(Model model);
    
    /**
     * Adiciona os valores padrão para uma nova partida
     * @param model Modelo Spring que será preenchido com os valores padrão
     */
    void adicionarValoresPadrao(Model model);
    
    /**
     * Valida se o usuário atual tem permissão para criar partidas
     * @return true se o usuário tem permissão, false caso contrário
     */
    boolean usuarioPodeCriarPartidas();
}
