package br.edu.ufersa.compare_city.comparador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComparadorController {
    @GetMapping("/cidades/comparar")
    @ResponseBody
    public String home(@RequestParam(name = "estado1") String estado1,
            @RequestParam(name = "cidade1") String cidade1,
            @RequestParam(name = "estado2") String estado2,
            @RequestParam(name = "cidade2") String cidade2) {
        return "Comparando " + cidade1 + " (" + estado1 + ") com " + cidade2 + " (" + estado2 + ")";
    }
}
