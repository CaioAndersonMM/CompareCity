package br.edu.ufersa.compare_city;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

        LinkedList<LinkedList<Double>> dadosEconomicosPorCidade = new LinkedList<>();
        Queue<Integer> dadosPopulacionais = new LinkedList<>();
        ArrayList<Double[]> dadosMeioAmbientePorCidade = new ArrayList<>();
        TreeMap<String, String> dadosBiomaESistemaCosteiro = new TreeMap<>();

        for (Cidade cidade : listaCidades) {
            LinkedList<Double> dadosEconomicos = new LinkedList<>();
            dadosEconomicos.add(cidade.getPibPerCapta());
            dadosEconomicos.add(cidade.getPercentualReceitasExternas());
            dadosEconomicos.add(cidade.getIdh());
            dadosEconomicos.add(cidade.getReceitasRealizadas());
            dadosEconomicos.add(cidade.getDespesasEmpenhadas());
            dadosEconomicosPorCidade.add(dadosEconomicos);

            dadosPopulacionais.add(cidade.getPopulacao());
            dadosPopulacionais.add(cidade.getDensidadeDemografica());

            Double[] dadosMeioAmbiente = {
                    cidade.getAreaUrbanizada(),
                    cidade.getEsgotamentoSanitario(),
                    cidade.getPercentualArborizacao(),
                    cidade.getPercentualUrbanizacaoViasPublicas(),
                    (double) cidade.getPopulacaoExpostaRisco()
            };

            dadosMeioAmbientePorCidade.add(dadosMeioAmbiente);
            dadosBiomaESistemaCosteiro.put(cidade.getBioma(), cidade.getSistemaCosteiroMarinho());

        }

        Stack<LocalDate> historico = cidadeRepository.lerPilha(); 


        model.addAttribute("cidades", listaCidades);
        model.addAttribute("dadosEconomicos", dadosEconomicosPorCidade);
        model.addAttribute("dadosPopulacionais", dadosPopulacionais);
        model.addAttribute("dadosMeioAmbiente", dadosMeioAmbientePorCidade);
        model.addAttribute("dadosBiomaESistemaCosteiro", dadosBiomaESistemaCosteiro);
        model.addAttribute("historico", historico);


        return "comparador";
    }
}
