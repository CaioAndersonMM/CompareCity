package br.edu.ufersa.compare_city.cidade;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CidadeController {

    private CidadesService cidadesService;

    public CidadeController() {
    }

    public CidadeController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }

    public static void main(String[] args) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Ler o arquivo JSON e converter para uma lista de objetos Cidade
                List<Cidade> cidades = objectMapper.readValue(new File("dados.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Cidade.class));

                // Exibir as cidades
                for (Cidade cidade : cidades) {
                    System.out.println(cidade);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
