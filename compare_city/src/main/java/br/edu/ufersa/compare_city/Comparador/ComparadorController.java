package br.edu.ufersa.compare_city.comparador;

import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufersa.compare_city.cidade.Cidade;
import br.edu.ufersa.compare_city.cidade.CidadeController;

@Controller
public class ComparadorController {

    private CidadeController cidadeController = new CidadeController();
    

    @GetMapping("/cidades/comparar")
    public String home(@RequestParam(name = "estado1") String estado1,
                       @RequestParam(name = "cidade1") String cidade1,
                       @RequestParam(name = "estado2") String estado2,
                       @RequestParam(name = "cidade2") String cidade2) {
        
        return cidadeController.buscarCidades(cidade1, estado1, cidade2, estado2);
    }


    @GetMapping("/cidades/comparacao")
    public String compararCidades(Model model) {
        List<Cidade> listaCidades = cidadeController.getListaCidades();
        Stack<LocalDate> historico = cidadeController.getHistorico();
        Comparador comparador = new Comparador(listaCidades);
        // System.out.println(listaCidades);

        model.addAttribute("cidades", listaCidades);
        model.addAttribute("dadosEconomicos", comparador.getDadosEconomicos());
        model.addAttribute("dadosPopulacionais", comparador.getDadosPopulacionais());
        model.addAttribute("dadosMeioAmbiente", comparador.getDadosMeioAmbiente());
        model.addAttribute("dadosBiomaESistemaCosteiro", comparador.getDadosBiomaESistemaCosteiro());
        model.addAttribute("historico", historico);

        return "comparador";
    }
}