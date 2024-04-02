package br.edu.ufersa.compare_city.cidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CidadeRepository {

    private static ArrayList<Cidade> listaCidades = new ArrayList<>();

    public CidadeRepository() {

    }

    public CidadeRepository(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        buscarCidade(cidadeUm, ufUm, cidadeDois, ufDois);
    }

    /**
     * <p>
     * Busca as cidades passadas como parâmetro no site do {@code IBGE Cidades}
     * e raspa os dados coletados para um arquivo Json
     * 
     * <p>
     * Após raspado, o dado será extraído quando chamado o método
     * {@link compare_city.src.main.java.br.edu.bufersa.compara_city.Cidade.CidadeRepository#extrairCidade()
     * extrairCidade()}
     * 
     * @param cidadeUm   - nome da primeira cidade a ser pesquisada
     * @param cidadeDois - nome da segunda cidade a ser pesquisada
     * @param ufUm       - unidade federativa da primeira cidade
     * @param ufDois     - unidade federativa da segunda cidade
     * @see (IBGE Cidades) https://cidades.ibge.gov.br/
     **/
    public void buscarCidade(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        try {
            ProcessBuilder procBuilder = new ProcessBuilder("python",
                    System.getProperty("user.dir") + "/index.py",
                    cidadeUm, ufUm, cidadeDois, ufDois);
            Process process = procBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.getMessage();
            e.printStackTrace();
        }
        extrairCidade();
    }

    /**
     * <p>
     * Extrai os dados do arquivo Json e o transforma numa classe Cidade
     * e adiciona a lista de cidades buscadas
     * 
     */
    public List<Cidade> extrairCidade() {
        ObjectMapper objectMapper = new ObjectMapper();
        Stack<LocalDate> dataAtual = new Stack<>(); // Pilha para armazenar a data atual

        // Lendo a pilha do arquivo binário
        dataAtual = lerPilha();
 
        try {
            @SuppressWarnings("unchecked")
            Map<String, Map<String, String>> map = objectMapper.readValue(new File("dados.json"), Map.class);

            for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                String nome = entry.getKey();
                Map<String, String> cidadeInfo = entry.getValue();

                Cidade cidade = new Cidade();

                cidade.setNome(nome);
                cidade.setUf(cidadeInfo.get("uf"));
                cidade.setPopulacao(Integer.parseInt(cidadeInfo.get("populacao").replaceAll("[^0-9]", "")));
                cidade.setDensidadeDemografica(
                        Integer.parseInt(cidadeInfo.get("densidade_demografica").replaceAll("[^0-9]", "")));
                cidade.setSalarioMedio(Double
                        .parseDouble(cidadeInfo.get("salario_medio").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPessoalOcupado(Integer.parseInt(cidadeInfo.get("pessoal_ocupado").replaceAll("[^0-9]", "")));
                cidade.setPopulacaoOcupada(Double
                        .parseDouble(cidadeInfo.get("populacao_ocupada").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualRendimentoNominalPerCapta(Double.parseDouble(
                        cidadeInfo.get("percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo")
                                .replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setTaxaEscolarizacao(Double.parseDouble(
                        cidadeInfo.get("taxa_escolarizacao").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdebInicioFundamental(Double.parseDouble(
                        cidadeInfo.get("ideb_inicio_fundamental").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdebFinalFundamental(Double.parseDouble(
                        cidadeInfo.get("ideb_final_fundamental").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setMatriculasFundamental(
                        Integer.parseInt(cidadeInfo.get("matriculas_fundamental").replaceAll("[^0-9]", "")));
                cidade.setMatriculasMedio(
                        Integer.parseInt(cidadeInfo.get("matriculas_medio").replaceAll("[^0-9]", "")));
                cidade.setDocentesFundamental(
                        Integer.parseInt(cidadeInfo.get("docentes_fundamental").replaceAll("[^0-9]", "")));
                cidade.setDocentesMedio(Integer.parseInt(cidadeInfo.get("docentes_medio").replaceAll("[^0-9]", "")));
                cidade.setEstabelecimentosFundamental(Integer
                        .parseInt(cidadeInfo.get("estabelecimentos_ensino_fundamental").replaceAll("[^0-9]", "")));
                cidade.setEstabelecimentosMedio(
                        Integer.parseInt(cidadeInfo.get("estabelecimentos_ensino_medio").replaceAll("[^0-9]", "")));
                cidade.setPibPerCapta(
                        Double.parseDouble(cidadeInfo.get("pib_percapita").replaceAll("[^0-9.,]", "").split(",")[0]
                                .replaceAll(",", "").trim()));
                cidade.setPercentualReceitasExternas(Double.parseDouble(
                        cidadeInfo.get("percentual_receitas_externas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setIdh(Double.parseDouble(cidadeInfo.get("idh").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setReceitasRealizadas(Double
                        .parseDouble(cidadeInfo.get("receitas_realizadas").replaceAll("[^0-9.,]", "").split(",")[0]
                                .replaceAll(",", "").trim()));
                cidade.setDespesasEmpenhadas(Double
                        .parseDouble(cidadeInfo.get("despesas_empenhadas").replaceAll("[^0-9.,]", "").split(",")[0]
                                .replaceAll(",", "").trim()));
                cidade.setMortalidadeInfantil(Double.parseDouble(
                        cidadeInfo.get("mortalidade_infantil").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setEstabelecimentosSaude(Double.parseDouble(
                        cidadeInfo.get("estabelecimentos_saude").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setAreaUrbanizada(Double
                        .parseDouble(cidadeInfo.get("area_urbanizada").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setEsgotamentoSanitario(Double.parseDouble(
                        cidadeInfo.get("esgotamento_sanitario").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualArborizacao(Double.parseDouble(
                        cidadeInfo.get("percentual_arborizacao").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPercentualUrbanizacaoViasPublicas(Double.parseDouble(cidadeInfo
                        .get("percentual_urbanizacao_vias_publicas").replaceAll("[^0-9.,]", "").replace(',', '.')));
                cidade.setPopulacaoExpostaRisco(
                        Integer.parseInt(cidadeInfo.get("populacao_exposta_risco").replaceAll("[^0-9]", "")));
                cidade.setBioma(cidadeInfo.get("bioma"));
                cidade.setSistemaCosteiroMarinho(cidadeInfo.get("sistema_costeiro_marinho"));

                listaCidades.add(cidade);
            }

            // GRAVA PILHA NUM ARQUIVO BINARIO
            dataAtual.push(LocalDate.now());
            escreverPilha(dataAtual);

        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return listaCidades;

    }

    public ArrayList<Cidade> getListaCidades() {
        return listaCidades;
    }

    public static Stack<LocalDate> lerPilha() {
        Stack<LocalDate> dataAtual = new Stack<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("historico.bin"))) {
            dataAtual = (Stack<LocalDate>) inputStream.readObject();
            System.out.println("Pilha lida com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando arquivo...");
            escreverPilha(dataAtual); // Cria um novo arquivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataAtual;
    }

    public static void escreverPilha(Stack<LocalDate> dataAtual) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("historico.bin"))) {
            outputStream.writeObject(dataAtual);
            System.out.println("Pilha gravada!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}