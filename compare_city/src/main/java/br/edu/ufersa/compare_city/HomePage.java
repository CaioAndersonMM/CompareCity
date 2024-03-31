package br.edu.ufersa.compare_city;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import br.edu.ufersa.compare_city.cidade.Cidade;
import br.edu.ufersa.compare_city.cidade.CidadeRepository;


import org.springframework.ui.Model;


@Controller
public class HomePage {

    private final CidadeRepository cidadeRepository = new CidadeRepository();


    @GetMapping("/")
    public String home() {
        return "index"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @GetMapping("/cidades/comparacao")
    public String compararCidades(Model model) {
        List<Cidade> listaCidades = cidadeRepository.extrairCidade();
        System.out.println(listaCidades);
        model.addAttribute("cidades", listaCidades);
        return "comparador";
    }
}
