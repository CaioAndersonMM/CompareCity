package br.edu.ufersa.compare_city.Comparador;

import br.edu.ufersa.compare_city.Cidade.Cidade;

public class Comparador {
    
    private Cidade cidadeUm;
    private Cidade cidadeDois;
    
    
    public Comparador() {
    }
    
    public Comparador(Cidade cidadeUm, Cidade cidadeDois) {
        this.cidadeUm = cidadeUm;
        this.cidadeDois = cidadeDois;
    }




    public Cidade getCidadeUm() {
        return cidadeUm;
    }
    public void setCidadeUm(Cidade cidadeUm) {
        this.cidadeUm = cidadeUm;
    }
    public Cidade getCidadeDois() {
        return cidadeDois;
    }
    public void setCidadeDois(Cidade cidadeDois) {
        this.cidadeDois = cidadeDois;
    }


    public boolean compararCidades() {
        
        return false;
    }
    
}
