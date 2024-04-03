package br.edu.ufersa.compare_city.comparador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComparadorController {
    
    @GetMapping("/cidades/comparar")
    public String home(@RequestParam(name = "estado1") String estado1,
            @RequestParam(name = "cidade1") String cidade1,
            @RequestParam(name = "estado2") String estado2,
            @RequestParam(name = "cidade2") String cidade2) {
        try {

            if (estado1.isEmpty() || estado2.isEmpty()) { //dados ficticios
                estado1 = "rn";
                estado2 = "rn";
                cidade1 = "jucurutu";
                cidade2 = "mossoro";
            }

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
                return "Erro ao executar script Python: " + errorOutput.toString();
            }

            return "redirect:/cidades/comparacao";
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Erro ao executar script Python: " + e.getMessage();
        }
    }
}