package br.edu.ufersa.compare_city.comparador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import br.edu.ufersa.compare_city.cidade.Cidade;

public class Comparador {

    private Cidade cidadeUm;
    private Cidade cidadeDois;

    private ArrayList<Cidade> listaCidades;
    private LinkedList<LinkedList<Double>> dadosEconomicos;
    private Queue<Double> dadosPopulacionais;
    private ArrayList<Double[]> dadosMeioAmbiente;
    private TreeMap<String, String> dadosBiomaESistemaCosteiro;


    public Comparador() {}

    public Comparador(List<Cidade> listaCidade) {
        this.listaCidades = (ArrayList<Cidade>) listaCidade;
        setCidadeUm(listaCidades.get(0));
        setCidadeDois(listaCidades.get(1));
        compararCidades();
    }

    public Cidade getCidadeUm() {
        return cidadeUm;
    }

    private void setCidadeUm(Cidade cidadeUm) {
        this.cidadeUm = cidadeUm;
    }

    public Cidade getCidadeDois() {
        return cidadeDois;
    }

    private void setCidadeDois(Cidade cidadeDois) {
        this.cidadeDois = cidadeDois;
    }

    public LinkedList<LinkedList<Double>> getDadosEconomicos() {
        return dadosEconomicos;
    }

    public Queue<Double> getDadosPopulacionais() {
        return dadosPopulacionais;
    }

    public ArrayList<Double[]> getDadosMeioAmbiente() {
        return dadosMeioAmbiente;
    }

    public TreeMap<String, String> getDadosBiomaESistemaCosteiro() {
        return dadosBiomaESistemaCosteiro;
    }



    public void compararCidades() {

        dadosEconomicos = new LinkedList<>();
        dadosPopulacionais = new ConcurrentLinkedQueue<>();
        dadosMeioAmbiente = new ArrayList<>();
        dadosBiomaESistemaCosteiro = new TreeMap<>();

        for (Cidade cidade : listaCidades) {
            LinkedList<Double> dadosE = new LinkedList<>();
            dadosE.add(cidade.getSalarioMedio());
            dadosE.add(cidade.getPibPerCapita());
            dadosE.add(cidade.getPercentualReceitasExternas());
            dadosE.add(cidade.getIdh());
            dadosE.add(cidade.getReceitasRealizadas());
            dadosE.add(cidade.getDespesasEmpenhadas());
            dadosEconomicos.add(dadosE);

            dadosPopulacionais.add(Double.valueOf(cidade.getPopulacao()));
            dadosPopulacionais.add(cidade.getDensidadeDemografica());

            Double[] dadosMeioA = {
                cidade.getAreaUrbanizada(),
                cidade.getEsgotamentoSanitario(),
                cidade.getPercentualArborizacao(),
                cidade.getPercentualUrbanizacaoViasPublicas(),
                (double) cidade.getPopulacaoExpostaRisco()
            };

            dadosMeioAmbiente.add(dadosMeioA);
            dadosBiomaESistemaCosteiro.put(cidade.getBioma(), cidade.getSistemaCosteiroMarinho());
        }

    }
}