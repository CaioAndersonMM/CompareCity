package br.edu.ufersa.compare_city.Cidade;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class CidadeRepository {

    private Cidade cidadeBuscada;
    private static ArrayList<Cidade> cidadesBuscadas = new ArrayList<>();

    public CidadeRepository() {

    }
    
    public CidadeRepository(String cidade) {
        buscarCidade(cidade);
    }

    /** busca a cidade no site do 
     * {@code IBGE cidades} e atribui o resultado a 
     * {@link br.edu.ufersa.compare_city.Cidade.CidadeRepository#cidadeBuscada cidadeBuscada} 
     * e adiciona a cidade a lista de {@link br.edu.ufersa.compare_city.Cidade.CidadeRepository#cidadesBuscadas cidadesBuscadas}
     * 
     * @param cidade - nome da cidade a ser buscada
     * */
    public void buscarCidade(String cidade) {
        /*
         * código de busca da cidade
         */
        cidadesBuscadas.add(cidadeBuscada);
    }

    public Cidade getCidade() {
        return cidadeBuscada;
    }

    public ArrayList<Cidade> getListaCidades() {
        return cidadesBuscadas;
    }

}
