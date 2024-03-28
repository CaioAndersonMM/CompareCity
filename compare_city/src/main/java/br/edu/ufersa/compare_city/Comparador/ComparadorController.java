package br.edu.ufersa.compare_city.comparador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.edu.ufersa.compare_city.cidade.Cidade;
import br.edu.ufersa.compare_city.cidade.CidadeRepository;

@Controller
public class ComparadorController {

    private final RestTemplate restTemplate;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public ComparadorController(CidadeRepository cidadeRepository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.cidadeRepository = cidadeRepository;
    }



    @GetMapping("/cidades/comparar")
    @ResponseBody
    public String home(@RequestParam(name = "estado1") String estado1,
            @RequestParam(name = "cidade1") String cidade1,
            @RequestParam(name = "estado2") String estado2,
            @RequestParam(name = "cidade2") String cidade2, Model model) {
        try {

            String mensagem = "Sua mensagem aqui"; // Defina a mensagem que deseja exibir

            // Adiciona a mensagem ao modelo para ser acessada pelo HTML
            model.addAttribute("mensagem", mensagem);

            if (estado1.isEmpty() || estado2.isEmpty()) { //dados ficticios
                estado1 = "rn";
                estado2 = "rn";
                cidade1 = "jucurutu";
                cidade2 = "mossoro";
            }

            model.addAttribute("mensagem", "Seus dados de estado e cidade estão sendo analisados!");
            // Executando o script Python
            String rootPath = System.getProperty("user.dir");

            ProcessBuilder pb = new ProcessBuilder("python", rootPath + "/index.py", estado1, cidade1, estado2, cidade2);

            Process p = pb.start();

            // Lendo a saída do script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Aguardando o término do script Python
            int exitCode = p.waitFor();

            // Verificando se houve algum erro
            if (exitCode != 0) {
                // Lendo a saída de erro, se houver
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                StringBuilder errorOutput = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    errorOutput.append(errorLine).append("\n");
                }
                model.addAttribute("mensagem", "Erro ao executar script Python: " + errorOutput.toString());
                return "Erro ao executar script Python: " + errorOutput.toString();
            }

            List<Cidade> listaCidades = cidadeRepository.extrairCidade();
            enviarCidadesRotas(listaCidades);

            return "pagina_de_sucesso"; // Página de sucesso personalizada
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Erro ao executar script Python: " + e.getMessage();
        }
    }

    private void enviarCidadesRotas(List<Cidade> listaCidades) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Cidade>> requestEntity = new HttpEntity<>(listaCidades, headers);
        String compararCidadesUrl = "http://localhost:8080/cidades/comparacao"; // Substitua pela URL correta da rota compararCidades
        restTemplate.postForObject(compararCidadesUrl, requestEntity, String.class);
    }
}