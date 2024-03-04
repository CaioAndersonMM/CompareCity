import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()


mapeamento_campos = {
    "População no último censo [2022]": "populacao",
    "Densidade demográfica [2022]": "densidade_demografica",
    "Salário médio mensal dos trabalhadores formais [2021]": "salario_medio",
    "Pessoal ocupado [2021]": "pessoal_ocupado",
    "População ocupada [2021]": "populacao_ocupada",
    "Percentual da população com rendimento nominal mensal per capita de até 1/2 salário mínimo [2010]": "percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo",
    "Taxa de escolarização de 6 a 14 anos de idade [2010]": "taxa_escolarizacao",
    "IDEB – Anos iniciais do ensino fundamental (Rede pública) [2021]": "ideb_inicio_fundamental",
    "IDEB – Anos finais do ensino fundamental (Rede pública) [2021]": "ideb_final_fundamental",
    "Matrículas no ensino fundamental [2021]": "matriculas_fundamental",
    "Matrículas no ensino médio [2021]": "matriculas_medio",
    "Docentes no ensino fundamental [2021]": "docentes_fundamental",
    "Docentes no ensino médio [2021]": "docentes_medio",
    "Número de estabelecimentos de ensino fundamental [2021]": "estabelecimentos_ensino_fundamental",
    "Número de estabelecimentos de ensino médio [2021]": "estabelecimentos_ensino_medio",
    "PIB per capita [2021]": "pib_percapita",
    "Percentual das receitas oriundas de fontes externas [2015]": "percentual_receitas_externas",
    "Índice de Desenvolvimento Humano Municipal (IDHM) [2010]": "idh",
    "Total de receitas realizadas [2017]": "receitas_realizadas",
    "Total de despesas empenhadas [2017]": "despesas_empenhadas",
    "Mortalidade Infantil [2020]": "mortalidade_infantil",
    "Internações por diarreia [2016]": "internacoes_diarreia",
    "Estabelecimentos de Saúde SUS [2009]": "estabelecimentos_saude",
    "Área urbanizada [2019]": "area_urbanizada",
    "Esgotamento sanitário adequado [2010]": "esgotamento_sanitario",
    "Arborização de vias públicas [2010]": "percentual_arborizacao",
    "Urbanização de vias públicas [2010]": "percentual_urbanizacao_vias_publicas",
    "População exposta ao risco [2010]": "populacao_exposta_risco",
    "Bioma [2019]": "bioma",
    "Sistema Costeiro-Marinho [2019]": "sistema_costeiro_marinho"
}


def clicar_proximo_cabecalho(indice):
    try:
        cabecalhos = driver.find_elements(By.CSS_SELECTOR, "tr.lista__cabecalho")
        
        if indice < len(cabecalhos):
            cabecalhos[indice].click()
            return True
        else:
            return False

    except Exception as e:
        print(f"Erro ao clicar no próximo cabeçalho: {e}")
        return False

try:
    
    cidade = "jucurutu"
    uf = "rn"

    driver.get(f"https://cidades.ibge.gov.br/brasil/{uf}/{cidade}/panorama")

    indice_cabecalho = 0
    data = {'uf': uf}
    forJson = False # Servirá de contador para escrita no arquivo
    indicadorAnterior = '' # Texto capturado anterior que será o campo do Json
    
    for _ in range(6):
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "//section[@id='dados']//tr"))
        )

        # Todas as células (td) dentro das linhas da tabela
        celulas = driver.find_elements(By.XPATH, "//section[@id='dados']//tr/td")
        textos_celulas = []

        for celula in celulas:
            if celula.text.strip():  # Ignorar células vazias
                if forJson is True:
                    data[mapeamento_campos[indicadorAnterior]] = celula.text
                    forJson = False
                else:
                    indicadorAnterior = celula.text
                    forJson = True
                    
                textos_celulas.append(celula.text)

        texto_completo = "\n".join(textos_celulas)
        print(texto_completo.encode('utf-8').decode('utf-8'))

        indice_cabecalho += 1

        if not clicar_proximo_cabecalho(indice_cabecalho):
            break  # Se não houver mais cabeçalhos, saia do loop

finally:
    driver.quit()
    
    dataJson = {cidade: data}
    
    with open("dados.json", "w") as file:
        json.dump(dataJson, file)
