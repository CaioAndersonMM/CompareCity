package br.edu.ufersa.compare_city.comparador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufersa.compare_city.cidade.CidadeController;

@Controller
public class ComparadorController {

    private CidadeController cidadeController = new CidadeController();
    
    @GetMapping("/cidades/comparar")
    public String home(@RequestParam(name = "estado1") String estado1,
                       @RequestParam(name = "cidade1") String cidade1,
                       @RequestParam(name = "estado2") String estado2,
                       @RequestParam(name = "cidade2") String cidade2) {
        
        return cidadeController.buscarCidade(cidade1, estado1, cidade2, estado2);
    }
}