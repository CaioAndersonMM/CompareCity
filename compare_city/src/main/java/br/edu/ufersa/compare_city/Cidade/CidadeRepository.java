package br.edu.ufersa.compare_city.Cidade;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;


@Repository
public class CidadeRepository {

    private String nomeCidadeUm;
    private String nomeCidadeDois;
    private Cidade cidadeBuscadaUm;
    private Cidade cidadeBuscadaDois;
    private static ArrayList<Cidade> listaCidades = new ArrayList<>();

    public CidadeRepository() {

    }
    
    public CidadeRepository(String cidade, String uf) {
        // buscarCidade(cidade, uf);
    }



    /** 
     * <p> Busca as cidades passadas como parâmetro no site do {@code IBGE Cidades} 
     * e raspa os dados coletados para um arquivo Json 
     * 
     * <p> Após raspado, o dado será extraído quando chamado o método 
     * {@link compare_city.src.main.java.br.edu.ufersa.compare_city.cidade.CidadeRepository#extrairCidade() extrairCidade()}
     * 
     * @param cidadeUm - nome da primeira cidade a ser pesquisada
     * @param cidadeDois - nome da segunda cidade a ser pesquisada
     * @param ufUm - unidade federativa da primeira cidade
     * @param ufDois - unidade federativa da segunda cidade
     * @see (IBGE Cidades) https://cidades.ibge.gov.br/ 
     **/
    public void buscarCidade(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        nomeCidadeUm = cidadeUm;
        nomeCidadeDois = cidadeDois;
        try {
            ProcessBuilder procBuilder = new ProcessBuilder("python", 
                                                            System.getProperty("user.dir")+"/index.py", 
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
     * <p> Extrai os dados do arquivo Json e o transforma numa classe Cidade 
     * e adiciona a lista de cidades buscadas
     * 
     */
    public void extrairCidade() {
        Cidade[] cidadesBuscadas = {cidadeBuscadaUm, cidadeBuscadaDois};
        String[] nomeDasCidades = {nomeCidadeUm, nomeCidadeDois};
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/index.py"));
            JSONObject cidade = (JSONObject) jsonObject.get(nomeDasCidades[0]);
            cidadesBuscadas[0].setNome(nomeDasCidades[0]);
            cidadesBuscadas[0].setUf((String) cidade.get("uf"));
            cidade.get("populacao");
            cidade.get("densidade_demografica");
            cidade.get("salario_medio");
            cidade.get("pessoal_ocupado");
            cidade.get("populacao_ocupada");
            cidade.get("percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo");
            cidade.get("taxa_escolarizacao");
            cidade.get("ideb_inicio_fundamental");
            cidade.get("ideb_final_fundamental");
            cidade.get("matriculas_fundamental");
            cidade.get("matriculas_medio");
            cidade.get("docentes_fundamental");
            cidade.get("docentes_medio");
            cidade.get("estabelecimentos_ensino_fundamental");
            cidade.get("estabelecimentos_ensino_medio");
            cidade.get("pib_percapita");
            cidade.get("percentual_receitas_externas");
            cidade.get("idh");
            cidade.get("receitas_realizadas");
            cidade.get("despesas_empenhadas");
            cidade.get("mortalidade_infantil");
            cidade.get("internacoes_diarreia");
            cidade.get("estabelecimentos_saude");
            cidade.get("area_urbanizada");
            cidade.get("esgotamento_sanitario");
            cidade.get("percentual_arborizacao");
            cidade.get("percentual_urbanizacao_vias_publicas");
            cidade.get("populacao_exposta_risco");
            cidade.get("bioma");
            cidade.get("sistema_costeiro_marinho");
            
        } catch (IOException | ParseException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public Cidade getCidade() {
        return cidadeBuscadaDois;
    }

    public ArrayList<Cidade> getListaCidades() {
        return listaCidades;
    }

}