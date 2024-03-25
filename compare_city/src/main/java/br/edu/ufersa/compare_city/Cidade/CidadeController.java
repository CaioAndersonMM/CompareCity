package br.edu.ufersa.compare_city.Cidade;

import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

@Controller
public class CidadeController {
    
    private CidadesService cidadesService;

    

    public CidadeController() {
    }

    public CidadeController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }


    
}
