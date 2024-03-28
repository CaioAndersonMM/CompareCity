import json
import re
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import sys


driver = webdriver.Chrome()

#BRUNO SUGERIU FAZER UM SISTEMA DE CONSULTA EM BATCH ai usaria LISTA encadeada!

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

def coletarDados(estado, cidade):

    driver.get(f"https://cidades.ibge.gov.br/brasil/{estado}/{cidade}/panorama")

    indice_cabecalho = 0
    data = {'uf': estado}
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
                    padrao_ano = r'\[\d{4}\]'
                    indicadorAnterior = re.sub(padrao_ano, '', celula.text).strip()
                    forJson = True
                    
                textos_celulas.append(celula.text)

        #texto_completo = "\n".join(textos_celulas)
        #print(texto_completo.encode('utf-8').decode('utf-8'))

        indice_cabecalho += 1

        if not clicar_proximo_cabecalho(indice_cabecalho):
            break  # Se não houver mais cabeçalhos, saia do loop
    
    dataJson = {cidade: data}
    
    try:
        with open("dados.json", "r") as file:
            existing_data = json.load(file)
    except json.decoder.JSONDecodeError: #Caso o arquivo esteja vazio!
        existing_data = {}
    
    with open("dados.json", "w") as file:
        json.dump(dataJson, file, indent=4)  # Indentação para melhor legibilidade

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
    coletarDados(sys.argv[1], sys.argv[2])
    
    coletarDados(sys.argv[3], sys.argv[4])
    
    driver.quit()

except Exception as e:

    print(f'Falha nos Dados: {e}')
