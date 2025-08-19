package br.edu.iff.com.agenda_futebol_amador.contracts.controllers.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ILoginSubmitController {
    @PostMapping(path = "/login")
    String handle(
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            Model model
    );
}
