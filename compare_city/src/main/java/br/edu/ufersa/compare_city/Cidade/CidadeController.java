package br.edu.ufersa.compare_city.cidade;


// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CidadeController {

    private CidadesService cidadesService;
    
    public CidadeController() {
    }
    
    public CidadeController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }
    
}