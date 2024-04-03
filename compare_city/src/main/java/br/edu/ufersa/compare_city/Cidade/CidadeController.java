package br.edu.ufersa.compare_city.cidade;


import java.time.LocalDate;
import java.util.List;
import java.util.Stack;


public class CidadeController {

    private CidadesService cidadesService;
    
    public CidadeController() {
        cidadesService = new CidadesService();
    }
    
    public CidadeController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }

    public String buscarCidade(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        return this.cidadesService.buscarCidade(cidadeUm, ufUm, cidadeDois, ufDois);
    }

    public List<Cidade> getListaCidades() {
        return this.cidadesService.getListaCidades();
    }

    public Stack<LocalDate> getHistorico() {
        return cidadesService.getHistorico();
    }
    
}