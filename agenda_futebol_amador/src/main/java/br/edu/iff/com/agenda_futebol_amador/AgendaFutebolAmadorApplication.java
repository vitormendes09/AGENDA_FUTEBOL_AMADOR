package br.edu.iff.com.agenda_futebol_amador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.edu.iff.com.agenda_futebol_amador.entities")
@EnableJpaRepositories("br.edu.iff.com.agenda_futebol_amador.repositories")
public class AgendaFutebolAmadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaFutebolAmadorApplication.class, args);
    }
}