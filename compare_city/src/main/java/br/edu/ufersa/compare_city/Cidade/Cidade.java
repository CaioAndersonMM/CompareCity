package br.edu.ufersa.compare_city.cidade;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cidade {
    /* informações principais */
     @JsonProperty("nome")
    private String nome;
    
    @JsonProperty("uf")
    private String uf;

    /* informações POPULAÇÃO */
    @JsonProperty("populacao")
    private int populacao;
    
    @JsonProperty("densidade_demografica")
    private int densidadeDemografica;

    /* informações TRABALHO E RENDIMENTO */
    @JsonProperty("salario_medio")
    private double salarioMedio;
    
    @JsonProperty("pessoal_ocupado")
    private int pessoalOcupado;
    
    @JsonProperty("populacao_ocupada")
    private double populacaoOcupada;
    
    @JsonProperty("percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo")
    private double percentualRendimentoNominalPerCapta;

    /* informações EDUCAÇÃO */
    @JsonProperty("taxa_escolarizacao")
    private double taxaEscolarizacao;
    
    @JsonProperty("ideb_inicio_fundamental")
    private double idebInicioFundamental;
    
    @JsonProperty("ideb_final_fundamental")
    private double idebFinalFundamental;
    
    @JsonProperty("matriculas_fundamental")
    private int matriculasFundamental;
    
    @JsonProperty("matriculas_medio")
    private int matriculasMedio;
    
    @JsonProperty("docentes_fundamental")
    private int docentesFundamental;
    
    @JsonProperty("docentes_medio")
    private int docentesMedio;
    
    @JsonProperty("estabelecimentos_ensino_fundamental")
    private int estabelecimentosFundamental;
    
    @JsonProperty("estabelecimentos_ensino_medio")
    private int estabelecimentosMedio;

    /* informações ECONOMIA */
    @JsonProperty("pib_percapita")
    private double pibPerCapta;
    
    @JsonProperty("percentual_receitas_externas")
    private double percentualReceitasExternas;
    
    @JsonProperty("idh")
    private double idh;
    
    @JsonProperty("receitas_realizadas")
    private double receitasRealizadas;
    
    @JsonProperty("despesas_empenhadas")
    private double despesasEmpenhadas;

    /* informações SAÚDE */
    @JsonProperty("mortalidade_infantil")
    private double mortalidadeInfantil;
    
    @JsonProperty("estabelecimentos_saude")
    private double estabelecimentosSaude;

    /* informações MEIO AMBIENTE */
    @JsonProperty("area_urbanizada")
    private double areaUrbanizada;
    
    @JsonProperty("esgotamento_sanitario")
    private double esgotamentoSanitario;
    
    @JsonProperty("percentual_arborizacao")
    private double percentualArborizacao;
    
    @JsonProperty("percentual_urbanizacao_vias_publicas")
    private double percentualUrbanizacaoViasPublicas;
    
    @JsonProperty("populacao_exposta_risco")
    private int populacaoExpostaRisco;
    
    @JsonProperty("bioma")
    private String bioma;
    
    @JsonProperty("sistema_costeiro_marinho")
    private String sistemaCosteiroMarinho;

    private LinkedList<Double> dadosEconomia = new LinkedList<>();


    /* Constructors */
    public Cidade() {
    }

    public Cidade(String nome, String uf, int populacao, int densidadeDemografica, double salarioMedio,
            int pessoalOcupado, double populacaoOcupada, double percentualRendimentoNominalPerCapta,
            double taxaEscolarizacao, double idebInicioFundamental, double idebFinalFundamental,
            int matriculasFundamental, int matriculasMedio, int docentesFundamental, int docentesMedio,
            int estabelecimentosFundamental, int estabelecimentosMedio, double pibPerCapta,
            double percentualReceitasExternas, double idh, double receitasRealizadas, double despesasEmpenhadas,
            double mortalidadeInfantil, double estabelecimentosSaude, double areaUrbanizada,
            double esgotamentoSanitario, double percentualArborizacao, double percentualUrbanizacaoViasPublicas,
            int populacaoExpostaRisco, String bioma, String sistemaCosteiroMarinho) {

        this.nome = nome;
        this.uf = uf;
        this.populacao = populacao;
        this.densidadeDemografica = densidadeDemografica;
        this.salarioMedio = salarioMedio;
        this.pessoalOcupado = pessoalOcupado;
        this.populacaoOcupada = populacaoOcupada;
        this.percentualRendimentoNominalPerCapta = percentualRendimentoNominalPerCapta;
        this.taxaEscolarizacao = taxaEscolarizacao;
        this.idebInicioFundamental = idebInicioFundamental;
        this.idebFinalFundamental = idebFinalFundamental;
        this.matriculasFundamental = matriculasFundamental;
        this.matriculasMedio = matriculasMedio;
        this.docentesFundamental = docentesFundamental;
        this.docentesMedio = docentesMedio;
        this.estabelecimentosFundamental = estabelecimentosFundamental;
        this.estabelecimentosMedio = estabelecimentosMedio;
        this.pibPerCapta = pibPerCapta;
        this.percentualReceitasExternas = percentualReceitasExternas;
        this.idh = idh;
        this.receitasRealizadas = receitasRealizadas;
        this.despesasEmpenhadas = despesasEmpenhadas;
        this.mortalidadeInfantil = mortalidadeInfantil;
        this.estabelecimentosSaude = estabelecimentosSaude;
        this.areaUrbanizada = areaUrbanizada;
        this.esgotamentoSanitario = esgotamentoSanitario;
        this.percentualArborizacao = percentualArborizacao;
        this.percentualUrbanizacaoViasPublicas = percentualUrbanizacaoViasPublicas;
        this.populacaoExpostaRisco = populacaoExpostaRisco;
        this.bioma = bioma;
        this.sistemaCosteiroMarinho = sistemaCosteiroMarinho;
    }

    public LinkedList<Double> getDadosEconomia() {
        dadosEconomia.clear();
        dadosEconomia.add(pibPerCapta);
        dadosEconomia.add(percentualReceitasExternas);
        dadosEconomia.add(idh);
        dadosEconomia.add(receitasRealizadas);
        dadosEconomia.add(despesasEmpenhadas);
        return dadosEconomia;
    }

    /* Gets and Sets */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public int getDensidadeDemografica() {
        return densidadeDemografica;
    }

    public void setDensidadeDemografica(int densidadeDemografica) {
        this.densidadeDemografica = densidadeDemografica;
    }

    public double getSalarioMedio() {
        return salarioMedio;
    }

    public void setSalarioMedio(double salarioMedio) {
        this.salarioMedio = salarioMedio;
    }

    public int getPessoalOcupado() {
        return pessoalOcupado;
    }

    public void setPessoalOcupado(int pessoalOcupado) {
        this.pessoalOcupado = pessoalOcupado;
    }

    public double getPopulacaoOcupada() {
        return populacaoOcupada;
    }

    public void setPopulacaoOcupada(double populacaoOcupada) {
        this.populacaoOcupada = populacaoOcupada;
    }

    public double getPercentualRendimentoNominalPerCapta() {
        return percentualRendimentoNominalPerCapta;
    }

    public void setPercentualRendimentoNominalPerCapta(double percentualRendimentoNominalPerCapta) {
        this.percentualRendimentoNominalPerCapta = percentualRendimentoNominalPerCapta;
    }

    public double getTaxaEscolarizacao() {
        return taxaEscolarizacao;
    }

    public void setTaxaEscolarizacao(double taxaEscolarizacao) {
        this.taxaEscolarizacao = taxaEscolarizacao;
    }

    public double getIdebInicioFundamental() {
        return idebInicioFundamental;
    }

    public void setIdebInicioFundamental(double idebInicioFundamental) {
        this.idebInicioFundamental = idebInicioFundamental;
    }

    public double getIdebFinalFundamental() {
        return idebFinalFundamental;
    }

    public void setIdebFinalFundamental(double idebFinalFundamental) {
        this.idebFinalFundamental = idebFinalFundamental;
    }

    public int getMatriculasFundamental() {
        return matriculasFundamental;
    }

    public void setMatriculasFundamental(int matriculasFundamental) {
        this.matriculasFundamental = matriculasFundamental;
    }

    public int getMatriculasMedio() {
        return matriculasMedio;
    }

    public void setMatriculasMedio(int matriculasMedio) {
        this.matriculasMedio = matriculasMedio;
    }

    public int getDocentesFundamental() {
        return docentesFundamental;
    }

    public void setDocentesFundamental(int docentesFundamental) {
        this.docentesFundamental = docentesFundamental;
    }

    public int getDocentesMedio() {
        return docentesMedio;
    }

    public void setDocentesMedio(int docentesMedio) {
        this.docentesMedio = docentesMedio;
    }

    public int getEstabelecimentosFundamental() {
        return estabelecimentosFundamental;
    }

    public void setEstabelecimentosFundamental(int estabelecimentosFundamental) {
        this.estabelecimentosFundamental = estabelecimentosFundamental;
    }

    public int getEstabelecimentosMedio() {
        return estabelecimentosMedio;
    }

    public void setEstabelecimentosMedio(int estabelecimentosMedio) {
        this.estabelecimentosMedio = estabelecimentosMedio;
    }

    public double getPibPerCapta() {
        return pibPerCapta;
    }

    public void setPibPerCapta(double pibPerCapta) {
        this.pibPerCapta = pibPerCapta;
    }

    public double getPercentualReceitasExternas() {
        return percentualReceitasExternas;
    }

    public void setPercentualReceitasExternas(double percentualReceitasExternas) {
        this.percentualReceitasExternas = percentualReceitasExternas;
    }

    public double getIdh() {
        return idh;
    }

    public void setIdh(double idh) {
        this.idh = idh;
    }

    public double getReceitasRealizadas() {
        return receitasRealizadas;
    }

    public void setReceitasRealizadas(double receitasRealizadas) {
        this.receitasRealizadas = receitasRealizadas;
    }

    public double getDespesasEmpenhadas() {
        return despesasEmpenhadas;
    }

    public void setDespesasEmpenhadas(double despesasEmpenhadas) {
        this.despesasEmpenhadas = despesasEmpenhadas;
    }

    public double getMortalidadeInfantil() {
        return mortalidadeInfantil;
    }

    public void setMortalidadeInfantil(double mortalidadeInfantil) {
        this.mortalidadeInfantil = mortalidadeInfantil;
    }

    public double getEstabelecimentosSaude() {
        return estabelecimentosSaude;
    }

    public void setEstabelecimentosSaude(double estabelecimentosSaude) {
        this.estabelecimentosSaude = estabelecimentosSaude;
    }

    public double getAreaUrbanizada() {
        return areaUrbanizada;
    }

    public void setAreaUrbanizada(double areaUrbanizada) {
        this.areaUrbanizada = areaUrbanizada;
    }

    public double getEsgotamentoSanitario() {
        return esgotamentoSanitario;
    }

    public void setEsgotamentoSanitario(double esgotamentoSanitario) {
        this.esgotamentoSanitario = esgotamentoSanitario;
    }

    public double getPercentualArborizacao() {
        return percentualArborizacao;
    }

    public void setPercentualArborizacao(double percentualArborizacao) {
        this.percentualArborizacao = percentualArborizacao;
    }

    public double getPercentualUrbanizacaoViasPublicas() {
        return percentualUrbanizacaoViasPublicas;
    }

    public void setPercentualUrbanizacaoViasPublicas(double percentualUrbanizacaoViasPublicas) {
        this.percentualUrbanizacaoViasPublicas = percentualUrbanizacaoViasPublicas;
    }

    public int getPopulacaoExpostaRisco() {
        return populacaoExpostaRisco;
    }

    public void setPopulacaoExpostaRisco(int populacaoExpostaRisco) {
        this.populacaoExpostaRisco = populacaoExpostaRisco;
    }

    public String getBioma() {
        return bioma;
    }

    public void setBioma(String bioma) {
        this.bioma = bioma;
    }

    public String getSistemaCosteiroMarinho() {
        return sistemaCosteiroMarinho;
    }

    public void setSistemaCosteiroMarinho(String sistemaCosteiroMarinho) {
        this.sistemaCosteiroMarinho = sistemaCosteiroMarinho;
    }
    public String toString() {
        return "Nome: " + nome +
                "\nUF: " + uf +
                "\nPopulação: " + populacao +
                "\nDensidade Demográfica: " + densidadeDemografica +
                "\nSalário Médio: " + salarioMedio +
                "\nPessoal Ocupado: " + pessoalOcupado +
                "\nPopulação Ocupada: " + populacaoOcupada +
                "\nPercentual de Rendimento Nominal Per Capita: " + percentualRendimentoNominalPerCapta +
                "\nTaxa de Escolarização: " + taxaEscolarizacao +
                "\nIDEB Início Fundamental: " + idebInicioFundamental +
                "\nIDEB Final Fundamental: " + idebFinalFundamental +
                "\nMatrículas no Fundamental: " + matriculasFundamental +
                "\nMatrículas no Médio: " + matriculasMedio +
                "\nDocentes no Fundamental: " + docentesFundamental +
                "\nDocentes no Médio: " + docentesMedio +
                "\nEstabelecimentos de Ensino Fundamental: " + estabelecimentosFundamental +
                "\nEstabelecimentos de Ensino Médio: " + estabelecimentosMedio +
                "\nPIB Per Capita: " + pibPerCapta +
                "\nPercentual de Receitas Externas: " + percentualReceitasExternas +
                "\nIDH: " + idh +
                "\nReceitas Realizadas: " + receitasRealizadas +
                "\nDespesas Empenhadas: " + despesasEmpenhadas +
                "\nMortalidade Infantil: " + mortalidadeInfantil +
                "\nEstabelecimentos de Saúde: " + estabelecimentosSaude +
                "\nÁrea Urbanizada: " + areaUrbanizada +
                "\nEsgotamento Sanitário: " + esgotamentoSanitario +
                "\nPercentual de Arborização: " + percentualArborizacao +
                "\nPercentual de Urbanização de Vias Públicas: " + percentualUrbanizacaoViasPublicas +
                "\nPopulação Exposta a Riscos: " + populacaoExpostaRisco +
                "\nBioma: " + bioma +
                "\nSistema Costeiro Marinho: " + sistemaCosteiroMarinho;
    }
}