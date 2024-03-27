import json
import re
import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()

mapeamento_campos = {
    "População no último censo": "populacao",
    "Densidade demográfica": "densidade_demografica",
    "Salário médio mensal dos trabalhadores formais": "salario_medio",
    "Pessoal ocupado": "pessoal_ocupado",
    "População ocupada": "populacao_ocupada",
    "Percentual da população com rendimento nominal mensal per capita de até 1/2 salário mínimo": "percentual_rendimento_nominal_per_capita_de_ate_dois_tercos_salario_minimo",
    "Taxa de escolarização de 6 a 14 anos de idade": "taxa_escolarizacao",
    "IDEB – Anos iniciais do ensino fundamental (Rede pública)": "ideb_inicio_fundamental",
    "IDEB – Anos finais do ensino fundamental (Rede pública)": "ideb_final_fundamental",
    "Matrículas no ensino fundamental": "matriculas_fundamental",
    "Matrículas no ensino médio": "matriculas_medio",
    "Docentes no ensino fundamental": "docentes_fundamental",
    "Docentes no ensino médio": "docentes_medio",
    "Número de estabelecimentos de ensino fundamental": "estabelecimentos_ensino_fundamental",
    "Número de estabelecimentos de ensino médio": "estabelecimentos_ensino_medio",
    "PIB per capita": "pib_percapita",
    "Percentual das receitas oriundas de fontes externas": "percentual_receitas_externas",
    "Índice de Desenvolvimento Humano Municipal (IDHM)": "idh",
    "Total de receitas realizadas": "receitas_realizadas",
    "Total de despesas empenhadas": "despesas_empenhadas",
    "Mortalidade Infantil": "mortalidade_infantil",
    "Internações por diarreia": "internacoes_diarreia",
    "Estabelecimentos de Saúde SUS": "estabelecimentos_saude",
    "Área urbanizada": "area_urbanizada",
    "Esgotamento sanitário adequado": "esgotamento_sanitario",
    "Arborização de vias públicas": "percentual_arborizacao",
    "Urbanização de vias públicas": "percentual_urbanizacao_vias_publicas",
    "População exposta ao risco": "populacao_exposta_risco",
    "Bioma": "bioma",
    "Sistema Costeiro-Marinho": "sistema_costeiro_marinho"
}

def coletarDados(cidade, estado):
    driver.get(f"https://cidades.ibge.gov.br/brasil/{estado}/{cidade}/panorama")

    indice_cabecalho = 0
    data = {'uf': estado}
    forJson = False
    indicadorAnterior = ''

    for _ in range(6):
        WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//section[@id='dados']//tr")))

        celulas = driver.find_elements(By.XPATH, "//section[@id='dados']//tr/td")

        for celula in celulas:
            if celula.text.strip():
                if forJson is True:
                    data[mapeamento_campos[indicadorAnterior]] = celula.text
                    forJson = False
                else:
                    padrao_ano = r'\[\d{4}\]'
                    indicadorAnterior = re.sub(padrao_ano, '', celula.text).strip()
                    forJson = True

        indice_cabecalho += 1

        if not clicar_proximo_cabecalho(indice_cabecalho):
            break

    dataJson = {cidade: data}

    with open("dados.json", "w", encoding="utf-8") as file:
        json.dump(dataJson, file, ensure_ascii=False, indent=4)

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
    
    coletarDados("jucurutu", "rn")

    coletarDados("mossoro", "rn")

    driver.quit()

except:
    print('Falha nos Dados')
