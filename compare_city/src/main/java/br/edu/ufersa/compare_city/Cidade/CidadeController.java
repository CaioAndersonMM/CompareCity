package br.edu.ufersa.compare_city.Cidade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class CidadeController {

    private CidadesService cidadesService;

    public CidadeController() {
    }

    public CidadeController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Cidade> cidades = new ArrayList<>();

        try {
            Map<String, Map<String, String>> map = objectMapper.readValue(new File("dados.json"),Map.class);

            // Iterar sobre o mapa e converter cada entrada em um objeto Cidade
            for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                String nome = entry.getKey();
                Map<String, String> cidadeInfo = entry.getValue();

                // Criar um objeto Cidade e atribuir os valores dos campos
                Cidade cidade = new Cidade();
                cidade.setNome(nome);
                cidade.setUf(cidadeInfo.get("uf"));
                cidade.setPopulacao(Integer.parseInt(cidadeInfo.get("populacao").replaceAll("[^0-9]", "")));
                cidade.setDensidadeDemografica(Integer.parseInt(cidadeInfo.get("densidade_demografica").replaceAll("[^0-9]", "")));
                cidade.setSalarioMedio(Double.parseDouble(cidadeInfo.get("salario_medio").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPessoalOcupado(Integer.parseInt(cidadeInfo.get("pessoal_ocupado").replaceAll("[^0-9]", "")));
                cidade.setPopulacaoOcupada(Double.parseDouble(cidadeInfo.get("populacao_ocupada").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualRendimentoNominalPerCapta(Double.parseDouble(cidadeInfo.get("percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setTaxaEscolarizacao(Double.parseDouble(cidadeInfo.get("taxa_escolarizacao").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdebInicioFundamental(Double.parseDouble(cidadeInfo.get("ideb_inicio_fundamental").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdebFinalFundamental(Double.parseDouble(cidadeInfo.get("ideb_final_fundamental").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setMatriculasFundamental(Integer.parseInt(cidadeInfo.get("matriculas_fundamental").replaceAll("[^0-9]", "")));
                cidade.setMatriculasMedio(Integer.parseInt(cidadeInfo.get("matriculas_medio").replaceAll("[^0-9]", "")));
                cidade.setDocentesFundamental(Integer.parseInt(cidadeInfo.get("docentes_fundamental").replaceAll("[^0-9]", "")));
                cidade.setDocentesMedio(Integer.parseInt(cidadeInfo.get("docentes_medio").replaceAll("[^0-9]", "")));
                cidade.setEstabelecimentosFundamental(Integer.parseInt(cidadeInfo.get("estabelecimentos_ensino_fundamental").replaceAll("[^0-9]", "")));
                cidade.setEstabelecimentosMedio(Integer.parseInt(cidadeInfo.get("estabelecimentos_ensino_medio").replaceAll("[^0-9]", "")));
                cidade.setPibPerCapta(Double.parseDouble(cidadeInfo.get("pib_percapita").replaceAll("[^0-9.,]", "").replaceFirst(",", ".").replaceAll(",", "").trim()));
                cidade.setPercentualReceitasExternas(Double.parseDouble(cidadeInfo.get("percentual_receitas_externas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdh(Double.parseDouble(cidadeInfo.get("idh").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setReceitasRealizadas(Double.parseDouble(cidadeInfo.get("receitas_realizadas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setDespesasEmpenhadas(Double.parseDouble(cidadeInfo.get("despesas_empenhadas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setMortalidadeInfantil(Double.parseDouble(cidadeInfo.get("mortalidade_infantil").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setEstabelecimentosSaude(Double.parseDouble(cidadeInfo.get("estabelecimentos_saude").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setAreaUrbanizada(Double.parseDouble(cidadeInfo.get("area_urbanizada").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setEsgotamentoSanitario(Double.parseDouble(cidadeInfo.get("esgotamento_sanitario").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualArborizacao(Double.parseDouble(cidadeInfo.get("percentual_arborizacao").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualUrbanizacaoViasPublicas(Double.parseDouble(cidadeInfo.get("percentual_urbanizacao_vias_publicas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPopulacaoExpostaRisco(Integer.parseInt(cidadeInfo.get("populacao_exposta_risco").replaceAll("[^0-9]", "")));
                cidade.setBioma(cidadeInfo.get("bioma"));
                cidade.setSistemaCosteiroMarinho(cidadeInfo.get("sistema_costeiro_marinho"));

                // Adicionar a cidade à lista
                cidades.add(cidade);
            }
            for (Cidade cidade : cidades) {
                System.out.println(cidade.getNome());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
