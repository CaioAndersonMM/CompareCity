package br.edu.ufersa.compare_city.cidade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class CidadeRepository {

    private List<Cidade> cidadesBuscadas = new ArrayList<>(2);
    private static List<Cidade> todasAsCidades = new LinkedList<>();
    private final String rootPath = System.getProperty("user.dir");


    public CidadeRepository() {
    }

    public CidadeRepository(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        buscarCidades(cidadeUm, ufUm, cidadeDois, ufDois);
    }



    /**
     * <p>
     * Busca as cidades passadas como parâmetro no site do {@code IBGE Cidades}
     * e raspa os dados coletados para um arquivo Json
     * 
     * <p>
     * Após raspado, o dado será extraído quando chamado o método extrairCidade()
     * 
     * @param cidadeUm   - nome da primeira cidade a ser pesquisada
     * @param cidadeDois - nome da segunda cidade a ser pesquisada
     * @param ufUm       - unidade federativa da primeira cidade
     * @param ufDois     - unidade federativa da segunda cidade
     * @see (IBGE Cidades) https://cidades.ibge.gov.br/
     **/
    public String buscarCidades(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {

        cidadesBuscadas = new ArrayList<Cidade>(2);
        Cidade novaCidadeUm = new Cidade();
        Cidade novaCidadeDois = new Cidade();
        novaCidadeUm.setNome(cidadeUm); novaCidadeUm.setUf(ufUm);
        novaCidadeDois.setNome(cidadeDois); novaCidadeDois.setUf(ufDois);

        try {
            if (existe(novaCidadeUm)) {
                int indexUm = pegarPosicao(novaCidadeUm);
                novaCidadeUm = todasAsCidades.get(indexUm);
                cidadesBuscadas.add(novaCidadeUm);
                
                if (existe(novaCidadeDois)) {
                    int indexDois = pegarPosicao(novaCidadeDois);
                    novaCidadeDois = todasAsCidades.get(indexDois);
                    cidadesBuscadas.add(novaCidadeDois);
                    return "redirect:/cidades/comparacao";
                }
                buscarUmaCidade(cidadeDois, ufDois);
                extrairCidade(cidadeDois);
                return "redirect:/cidades/comparacao"; 
            }

            if (existe(novaCidadeDois)) {
                int index = pegarPosicao(novaCidadeDois);
                novaCidadeDois = todasAsCidades.get(index);
                cidadesBuscadas.add(novaCidadeDois);
                buscarUmaCidade(cidadeUm, ufUm);
                extrairCidade(cidadeUm);
                return "redirect:/cidades/comparacao";
            }

            /*
             * Cria novo arquvo Json para o script Python escrever nele
             */
            File arquivoJson = new File(rootPath + "/dados.json");
            if (!(arquivoJson.exists())) {    
                arquivoJson.createNewFile();
            }

            if (cidadeUm.equals(cidadeDois)) {
                long tempoInicial = System.currentTimeMillis();
                buscarUmaCidade(cidadeUm, ufUm);
                extrairCidade(cidadeUm);
                buscarUmaCidade(cidadeDois, ufDois);
                extrairCidade(cidadeDois);
                long tempoFinal = System.currentTimeMillis();
                System.out.println("Tempo total das duas buscas e extrações: "+(tempoFinal-tempoInicial)+"ms");
                return "redirect:/cidades/comparacao";
            }

            long timeInicial = System.currentTimeMillis();
            try {
                ProcessBuilder pb1 = new ProcessBuilder("python", rootPath + "/index.py", ufUm, cidadeUm);
                ProcessBuilder pb2 = new ProcessBuilder("python", rootPath + "/index.py", ufDois, cidadeDois);
                Process p1 = pb1.start();
                Process p2 = pb2.start();
                int exitCodeP1 = p1.waitFor();
                int exitCodeP2 = p2.waitFor();

                
                if (exitCodeP1 != 0) {

                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
                    StringBuilder errorOutput = new StringBuilder();
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                    errorOutput.append(errorLine).append("\n");
                    }
                    throw new Exception("Erro ao executar script Python: " + errorOutput.toString());
                }

                if (exitCodeP2 != 0) {

                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
                    StringBuilder errorOutput = new StringBuilder();
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                    errorOutput.append(errorLine).append("\n");
                    }
                    throw new Exception("Erro ao executar script Python: " + errorOutput.toString());
                }

                extrairCidade(cidadeUm, cidadeDois);

            } catch(IOException | InterruptedException e) {
                e.getMessage();
                e.printStackTrace();
                throw e;
            }
            long timeFinal = System.currentTimeMillis();
            System.out.println("tempo de execução dos dois processos do programa: " + (timeFinal - timeInicial) + "ms");
            
        } catch (Exception e) {return e.getMessage();}

        return "redirect:/cidades/comparacao";
    }




    /**
     * <p>
     * Executa o programa Python e raspa os dados da cidade especificada pelos parâmetros
     * 
     * @param cidade - nome da cidade a ser buscada
     * @param uf - nome da unidade federativa da cidade a ser buscada
     * @throws Exception caso algum erro ocorra na execução do programa de raspagem 
     */
    private void buscarUmaCidade(String cidade, String uf) throws Exception{
        long timeInicial = System.currentTimeMillis();

        File arquivoJson = new File(rootPath + "/dados.json");
        if (!arquivoJson.exists()) {    
            arquivoJson.createNewFile();
        }

        try {

            ProcessBuilder pb = new ProcessBuilder("python", rootPath + "/index.py", uf, cidade);
            Process p = pb.start();             // Executa o programa Python
            int exitCode = p.waitFor();         // Aguarda o término do programa Python
            
            if (exitCode != 0) {                // Verifica se houve algum erro na execução do programa

                BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                StringBuilder errorOutput = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    errorOutput.append(errorLine).append("\n");
                }
                throw new Exception("Erro ao executar script Python: " + errorOutput.toString());
            }
        } catch(IOException | InterruptedException e) {
            e.getMessage();
            e.printStackTrace();
        } 
        long timeFinal = System.currentTimeMillis();
        System.out.println("tempo de execução do programa Python: " + (timeFinal - timeInicial) + "ms");
    }

    


    /**
     * <p>
     * Extrai os dados do arquivo Json e o transforma numa classe Cidade
     * e adiciona a lista de cidades buscadas
     * 
     */
    private void extrairCidade(String... nomeCidades) {
        Stack<LocalDate> dataAtual = lerPilha();

        long timeInicial = System.currentTimeMillis();
        try {
            FileReader leitor = new FileReader(rootPath + "/dados.json");
            JSONParser parser = new JSONParser();
            JSONObject objetoJSON = (JSONObject) parser.parse(leitor);
            leitor.close();

            for (int i = 0; i < nomeCidades.length; i++) {
                JSONObject dadosJSON = (JSONObject) objetoJSON.get(nomeCidades[i]);
                Cidade cidade = new Cidade();


                cidade.setNome(nomeCidades[i]);
                
                String uf = (String) dadosJSON.get("uf");
                cidade.setUf(uf);

                String populacao = (String) dadosJSON.get("populacao");
                if (populacao.contains("Sem dados") || populacao.contains("- ")) {
                    cidade.setPopulacao(-1);
                } else {
                    populacao = populacao.replace(".", "");
                    populacao = populacao.split(" ")[0];
                    cidade.setPopulacao(Integer.parseInt(populacao));
                }
                
                String densidadeDemografica = (String) dadosJSON.get("densidade_demografica");
                if (densidadeDemografica.contains("Sem dados") || densidadeDemografica.contains("- ")) {
                    cidade.setDensidadeDemografica(-1);
                } else {
                    densidadeDemografica = densidadeDemografica.replace(".", "");
                    densidadeDemografica = densidadeDemografica.replace(",", ".");
                    densidadeDemografica = densidadeDemografica.split(" ")[0];
                    cidade.setDensidadeDemografica(Double.parseDouble(densidadeDemografica));
                }

                String salarioMedio = (String) dadosJSON.get("salario_medio");
                if (salarioMedio.contains("Sem dados") || salarioMedio.contains("- ")) {
                    cidade.setSalarioMedio(-1);
                } else {
                    salarioMedio = salarioMedio.replace(".", "");
                    salarioMedio = salarioMedio.replace(",", ".");
                    salarioMedio = salarioMedio.split(" ")[0];
                    cidade.setSalarioMedio(Double.parseDouble(salarioMedio));
                }

                String pessoalOcupado = (String) dadosJSON.get("pessoal_ocupado");
                if (pessoalOcupado.contains("Sem dados") || pessoalOcupado.contains("- ")) {
                    cidade.setPessoalOcupado(-1);
                } else {
                    pessoalOcupado = pessoalOcupado.replace(".", "");
                    pessoalOcupado = pessoalOcupado.split(" ")[0];
                    cidade.setPessoalOcupado(Integer.parseInt(pessoalOcupado));
                }

                String populacaoOcupada = (String) dadosJSON.get("populacao_ocupada");
                if (populacaoOcupada.contains("Sem dados") || populacaoOcupada.contains("- ")) {
                    cidade.setPopulacaoOcupada(-1);
                } else {
                    populacaoOcupada = populacaoOcupada.replace(",", ".");
                    populacaoOcupada = populacaoOcupada.split(" ")[0];
                    cidade.setPopulacaoOcupada(Double.parseDouble(populacaoOcupada));
                }

                String percentualRendimento = (String) dadosJSON.get("percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo");
                if (percentualRendimento.contains("Sem dados") || percentualRendimento.contains("- ")) {
                    cidade.setPercentualRendimentoNominalPerCapita(-1);
                } else {
                    percentualRendimento = percentualRendimento.replace(",", ".");
                    percentualRendimento = percentualRendimento.split(" ")[0];
                    cidade.setPercentualRendimentoNominalPerCapita(Double.parseDouble(percentualRendimento));
                }

                String taxaEscolarizacao = (String) dadosJSON.get("taxa_escolarizacao");
                if (taxaEscolarizacao.contains("Sem dados") || taxaEscolarizacao.contains("- ")) {
                    cidade.setTaxaEscolarizacao(-1);
                } else {
                    taxaEscolarizacao = taxaEscolarizacao.replace(",", ".");
                    taxaEscolarizacao = taxaEscolarizacao.split(" ")[0];
                    cidade.setTaxaEscolarizacao(Double.parseDouble(taxaEscolarizacao));
                }

                String idebInicio = (String) dadosJSON.get("ideb_inicio_fundamental");
                if (idebInicio.contains("Sem dados") || idebInicio.contains("- ")) {
                    cidade.setIdebInicioFundamental(-1);
                } else {
                    idebInicio = idebInicio.replace(",", ".");
                    idebInicio = idebInicio.split(" ")[0];
                    cidade.setIdebInicioFundamental(Double.parseDouble(idebInicio));
                }

                String idebFinal = (String) dadosJSON.get("ideb_final_fundamental");
                if (idebFinal.contains("Sem dados") || idebFinal.contains("- ")) {
                    cidade.setIdebFinalFundamental(-1);
                } else {
                    idebFinal = idebFinal.replace(",", ".");
                    idebFinal = idebFinal.split(" ")[0];
                    cidade.setIdebFinalFundamental(Double.parseDouble(idebFinal));
                }

                String matriculasFundamental = (String) dadosJSON.get("matriculas_fundamental");
                if (matriculasFundamental.contains("Sem dados") || matriculasFundamental.contains("- ")) {
                    cidade.setMatriculasFundamental(-1);
                } else {
                    matriculasFundamental = matriculasFundamental.replace(".", "");
                    matriculasFundamental = matriculasFundamental.split(" ")[0];
                    cidade.setMatriculasFundamental(Integer.parseInt(matriculasFundamental));
                }

                String matriculasMedio = (String) dadosJSON.get("matriculas_medio");
                if (matriculasMedio.contains("Sem dados") || matriculasMedio.contains("- ")) {
                    cidade.setMatriculasMedio(-1);
                } else {
                    matriculasMedio = matriculasMedio.replace(".", "");
                    matriculasMedio = matriculasMedio.split(" ")[0];
                    cidade.setMatriculasMedio(Integer.parseInt(matriculasMedio));
                }

                String docentesFundamental = (String) dadosJSON.get("docentes_fundamental");
                if (docentesFundamental.contains("Sem dados") || docentesFundamental.contains("- ")) {
                    cidade.setDocentesFundamental(-1);
                } else {
                    docentesFundamental = docentesFundamental.replace(".", "");
                    docentesFundamental = docentesFundamental.split(" ")[0];
                    cidade.setDocentesFundamental(Integer.parseInt(docentesFundamental));
                }
                
                String docentesMedio = (String) dadosJSON.get("docentes_medio");
                if (docentesMedio.contains("Sem dados") || docentesMedio.contains("- ")) {
                    cidade.setDocentesMedio(-1);
                } else {
                    docentesMedio = docentesMedio.replace(".", "");
                    docentesMedio = docentesMedio.split(" ")[0];
                    cidade.setDocentesMedio(Integer.parseInt(docentesMedio));
                }

                String escolaFundamental = (String) dadosJSON.get("estabelecimentos_ensino_fundamental");
                if (escolaFundamental.contains("Sem dados") || escolaFundamental.contains("- ")) {
                    cidade.setEstabelecimentosFundamental(-1);
                } else {
                    escolaFundamental = escolaFundamental.replace(".", "");
                    escolaFundamental = escolaFundamental.split(" ")[0];
                    cidade.setEstabelecimentosFundamental(Integer.parseInt(escolaFundamental));
                }

                String escolaMedio = (String) dadosJSON.get("estabelecimentos_ensino_medio");
                if (escolaMedio.contains("Sem dados") || escolaMedio.contains("- ")) {
                    cidade.setEstabelecimentosMedio(-1);
                } else {
                    escolaMedio = escolaMedio.replace(".", "");
                    escolaMedio = escolaMedio.split(" ")[0];
                    cidade.setEstabelecimentosMedio(Integer.parseInt(escolaMedio));
                }

                String pibPerCapita = (String) dadosJSON.get("pib_percapita");
                if (pibPerCapita.contains("Sem dados") || pibPerCapita.contains("- ")) {
                    cidade.setPibPerCapita(-1);
                } else {
                    pibPerCapita = pibPerCapita.replace(".", "");
                    pibPerCapita = pibPerCapita.replace(",", ".");
                    pibPerCapita = pibPerCapita.split(" ")[0];
                    cidade.setPibPerCapita(Double.parseDouble(pibPerCapita));
                }

                String percentualReceitas = (String) dadosJSON.get("percentual_receitas_externas");
                if (percentualReceitas.contains("Sem dados") || percentualReceitas.contains("- ")) {
                    cidade.setPercentualReceitasExternas(-1);
                } else {
                    percentualReceitas = percentualReceitas.replace(",", ".");
                    percentualReceitas = percentualReceitas.split(" ")[0];
                    cidade.setPercentualReceitasExternas(Double.parseDouble(percentualReceitas));
                }

                String idh = (String) dadosJSON.get("idh");
                if (idh.contains("Sem dados") || idh.contains("- ")) {
                    cidade.setIdh(-1);
                } else {
                    idh = idh.replace(",", ".");
                    idh = idh.split(" ")[0];
                    cidade.setIdh(Double.parseDouble(idh));
                }

                String receitasRealizadas = (String) dadosJSON.get("receitas_realizadas");
                if (receitasRealizadas.contains("Sem dados") || receitasRealizadas.contains("- ")) {
                    cidade.setReceitasRealizadas(-1);
                } else {
                    receitasRealizadas = receitasRealizadas.replace(".", "");
                    receitasRealizadas = receitasRealizadas.replace(",", ".");
                    receitasRealizadas = receitasRealizadas.split(" ")[0];
                    cidade.setReceitasRealizadas(Double.parseDouble(receitasRealizadas));
                }

                String desesasEmpenhadas = (String) dadosJSON.get("despesas_empenhadas");
                if (desesasEmpenhadas.contains("Sem dados") || desesasEmpenhadas.contains("- ")) {
                    cidade.setDespesasEmpenhadas(-1);
                } else {
                    desesasEmpenhadas = desesasEmpenhadas.replace(".", "");
                    desesasEmpenhadas = desesasEmpenhadas.replace(",", ".");
                    desesasEmpenhadas = desesasEmpenhadas.split(" ")[0];
                    cidade.setDespesasEmpenhadas(Double.parseDouble(desesasEmpenhadas));
                }

                String mortalidadeInfantil = (String) dadosJSON.get("mortalidade_infantil");
                if (mortalidadeInfantil.contains("Sem dados") || mortalidadeInfantil.contains("- ")) {
                    cidade.setMortalidadeInfantil(-1);
                } else {
                    mortalidadeInfantil = mortalidadeInfantil.replace(".", "");
                    mortalidadeInfantil = mortalidadeInfantil.replace(",", ".");
                    mortalidadeInfantil = mortalidadeInfantil.split(" ")[0];
                    cidade.setMortalidadeInfantil(Double.parseDouble(mortalidadeInfantil));
                }

                String hospitais = (String) dadosJSON.get("estabelecimentos_saude");
                if (hospitais.contains("Sem dados") || hospitais.contains("- ")) {
                    cidade.setEstabelecimentosSaude(-1);
                } else {
                    hospitais = hospitais.replace(".", "");
                    hospitais = hospitais.split(" ")[0];
                    cidade.setEstabelecimentosSaude(Integer.parseInt(hospitais));
                }

                String areaUrbanizada = (String) dadosJSON.get("area_urbanizada");
                if (areaUrbanizada.contains("Sem dados") || areaUrbanizada.contains("- ")) {
                    cidade.setAreaUrbanizada(-1);
                } else {
                    areaUrbanizada = areaUrbanizada.replace(".", "");
                    areaUrbanizada = areaUrbanizada.replace(",", ".");
                    areaUrbanizada = areaUrbanizada.split(" ")[0];
                    cidade.setAreaUrbanizada(Double.parseDouble(areaUrbanizada));
                }

                String esgotamentoSanitario = (String) dadosJSON.get("esgotamento_sanitario");
                if (esgotamentoSanitario.contains("Sem dados") || esgotamentoSanitario.contains("- ")) {
                    cidade.setEsgotamentoSanitario(-1);
                } else {
                    esgotamentoSanitario = esgotamentoSanitario.replace(",", ".");
                    esgotamentoSanitario = esgotamentoSanitario.split(" ")[0];
                    cidade.setEsgotamentoSanitario(Double.parseDouble(esgotamentoSanitario));
                }

                String percentualArborizacao = (String) dadosJSON.get("percentual_arborizacao");
                if (percentualArborizacao.contains("Sem dados") || percentualArborizacao.contains("- ")) {
                    cidade.setPercentualArborizacao(-1);
                } else {
                    percentualArborizacao = percentualArborizacao.replace(",", ".");
                    percentualArborizacao = percentualArborizacao.split(" ")[0];
                    cidade.setPercentualArborizacao(Double.parseDouble(percentualArborizacao));
                }

                String percentualUrbanizacao = (String) dadosJSON.get("percentual_urbanizacao_vias_publicas");
                if (percentualUrbanizacao.contains("Sem dados") || percentualUrbanizacao.contains("- ")) {
                    cidade.setPercentualUrbanizacaoViasPublicas(-1);
                } else {
                    percentualUrbanizacao = percentualUrbanizacao.replace(",", ".");
                    percentualUrbanizacao = percentualUrbanizacao.split(" ")[0];
                    cidade.setPercentualUrbanizacaoViasPublicas(Double.parseDouble(percentualUrbanizacao));
                }

                String populacaoEmRisco = (String) dadosJSON.get("populacao_exposta_risco");
                if (populacaoEmRisco.contains("Sem dados") || populacaoEmRisco.contains("- ")) {
                    cidade.setPopulacaoExpostaRisco(-1);
                } else {
                    populacaoEmRisco = populacaoEmRisco.replace(".", "");
                    populacaoEmRisco = populacaoEmRisco.split(" ")[0];
                    cidade.setPopulacaoExpostaRisco(Integer.parseInt(populacaoEmRisco));
                }

                String bioma = (String) dadosJSON.get("bioma");
                bioma = bioma.trim();
                cidade.setBioma(bioma);
                
                String sistemaCosteiro = (String) dadosJSON.get("sistema_costeiro_marinho");
                sistemaCosteiro = sistemaCosteiro.trim();
                cidade.setSistemaCosteiroMarinho(sistemaCosteiro);


                cidadesBuscadas.add(cidade);
                todasAsCidades.add(cidade);
            }            
            

            // GRAVA PILHA NUM ARQUIVO BINARIO
            dataAtual.push(LocalDate.now());
            escreverPilha(dataAtual);

        } catch (IOException | ParseException e) {
            e.getMessage();
            e.printStackTrace();
        }

        long timeFinal = System.currentTimeMillis();
        System.out.println("tempo de execução da extração do Json: " + (timeFinal - timeInicial) + "ms");
        
        try {
            Files.deleteIfExists(Path.of(rootPath + "/dados.json"));
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }



    public List<Cidade> getListaCidades() {
        return cidadesBuscadas;
    }



    @SuppressWarnings("unchecked")
    public Stack<LocalDate> lerPilha() {
        Stack<LocalDate> dataAtual = new Stack<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("historico.bin"))) {
            dataAtual = (Stack<LocalDate>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando arquivo...");
            escreverPilha(dataAtual); // Cria um novo arquivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataAtual;
    }

    private void escreverPilha(Stack<LocalDate> dataAtual) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("historico.bin"))) {
            outputStream.writeObject(dataAtual);
        } catch (IOException e) {e.printStackTrace();}
    }



    private boolean existe(Cidade cidade) {
        for (Cidade city : todasAsCidades) {
            if (city.getUf().equals(cidade.getUf()) && city.getNome().equals(cidade.getNome()))
                return true;
        }
        return false;
    }

    private int pegarPosicao(Cidade cidade) {
        int cont = 0;
        for (Cidade city : todasAsCidades) {
            if (city.getUf().equals(cidade.getUf()) && city.getNome().equals(cidade.getNome())) {
                return cont;
            }
            cont++;                
        }
        return -1;
    }
}