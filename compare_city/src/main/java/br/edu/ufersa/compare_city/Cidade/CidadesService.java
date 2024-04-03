package br.edu.ufersa.compare_city.cidade;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CidadesService {

    private CidadeRepository cidadeRepository;

    public CidadesService() {
        cidadeRepository = new CidadeRepository();
    }

    public void buscarCidade(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        cidadeRepository.buscarCidade(cidadeUm, ufUm, cidadeDois, ufDois);
    }


    public ArrayList<Cidade> getListaCidades() {
        return cidadeRepository.getListaCidades();
    }

}
