package br.edu.ufersa.compare_city.Cidade;

public class Cidade {
    /* informações principais */
    private String nome;
    private String uf;

    /* informações POPULAÇÃO */
    private int populacao;
    private int densidadeDemografica;

    /* informações TRABALHO E RENDIMENTO */
    private double salarioMedio;
    private int pessoalOcupado;
    private double populacaoOcupada;
    private double percentualRendimentoNominalPerCapta;

    /* informações EDUCAÇÃO */
    private double taxaEscolarizacao;
    private double idebInicioFundamental;
    private double idebFinalFundamental;
    private int matriculasFundamental;
    private int matriculasMedio;
    private int docentesFundamental;
    private int docentesMedio;
    private int estabelecimentosFundamental;
    private int estabelecimentosMedio;

    /* informações ECONOMIA */
    private double pibPerCapta;
    private double percentualReceitasExternas;
    private double idh;
    private double receitasRealizadas;
    private double despesasEmpenhadas;

    /* informações SAÚDE */
    private double mortalidadeInfantil;
    private double estabelecimentosSaude;

    /* informações MEIO AMBIENTE */
    private double areaUrbanizada;
    private double esgotamentoSanitario;
    private double percentualArborizacao;
    private double percentualUrbanizacaoViasPublicas;
    private int populacaoExpostaRisco;
    private String bioma;
    private String sistemaCosteiroMarinho;
    
    
    
    
    /* Constructors */
    public Cidade() {}

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
}