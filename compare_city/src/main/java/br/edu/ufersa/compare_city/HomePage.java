package br.edu.ufersa.compare_city;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

    @GetMapping("/")
    public String home() {
        return "index"; // Retorna o nome do arquivo HTML sem a extensão
    }
}