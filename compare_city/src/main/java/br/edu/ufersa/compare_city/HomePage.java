package br.edu.ufersa.compare_city;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import br.edu.ufersa.compare_city.cidade.Cidade;

import org.springframework.ui.Model;


@Controller
public class HomePage {
    @GetMapping("/")
    public String home() {
        return "index"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @PostMapping("/cidades/comparacao")
    public String compararCidades(@RequestBody List<Cidade> listaCidades, Model model) {
        // Aqui você pode processar a lista de cidades como desejar
        model.addAttribute("cidades", listaCidades);
        return "comparador"; // Retorna o nome do arquivo HTML sem a extensão
    }
}
