package br.edu.ufersa.compare_city;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomePage {
    
    @GetMapping("/")
    public String home() {
        return "Página inicial do projeto";
    }
}
