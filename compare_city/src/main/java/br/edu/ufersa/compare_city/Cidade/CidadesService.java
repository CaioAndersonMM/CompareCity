package br.edu.ufersa.compare_city.cidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

import org.springframework.stereotype.Service;

@Service
public class CidadesService {

    private CidadeRepository cidadeRepository;

    public CidadesService() {
        cidadeRepository = new CidadeRepository();
    }

    public String buscarCidade(String cidadeUm, String ufUm, String cidadeDois, String ufDois) {
        return cidadeRepository.buscarCidade(cidadeUm, ufUm, cidadeDois, ufDois);
    }


    public ArrayList<Cidade> getListaCidades() {
        return cidadeRepository.getListaCidades();
    }

    public Stack<LocalDate> getHistorico() {
        return cidadeRepository.lerPilha();
    }

}