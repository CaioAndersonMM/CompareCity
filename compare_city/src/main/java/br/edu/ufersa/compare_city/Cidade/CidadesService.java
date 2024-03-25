package br.edu.ufersa.compare_city.Cidade;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CidadesService {

    private CidadeRepository cidadeRepository;


    public CidadesService() {
        cidadeRepository = new CidadeRepository();
    }



    public void buscarCidade(String cidade) {
        cidadeRepository.buscarCidade(cidade);
    }

    public Cidade getCidade() {
        return cidadeRepository.getCidade();
    }

    public ArrayList<Cidade> getListaCidades() {
        return cidadeRepository.getListaCidades();
    }
    
}
