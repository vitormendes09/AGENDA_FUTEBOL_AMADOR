package br.edu.iff.com.agenda_futebol_amador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path  = "principal")
public class MainViewController {

    @GetMapping("/")
    public String getHomePage() {
        return "index.html";
    }
    

}
