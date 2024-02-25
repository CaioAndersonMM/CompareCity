from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import pandas as pd

goiania = {"estado": "go", "cidade": "goiania"}
jucurutu = {"estado": "rn", "cidade": "jucurutu"}

estado = input("Estado: ")

if estado.lower() == "goiania":
    estado = goiania["estado"]
    cidade = goiania["cidade"]
elif estado.lower() == "jucurutu":
    estado = jucurutu["estado"]
    cidade = jucurutu["cidade"]
else:
    cidade = input("Cidade: ")

print("Estado:", estado)
print("Cidade:", cidade)

chrome_options = Options()
chrome_options.add_argument("--headless")
driver = webdriver.Chrome() 

driver = webdriver.Chrome(options=chrome_options) 
driver.implicitly_wait(10)  

url = f'https://cidades.ibge.gov.br/brasil/{estado}/{cidade}/panorama'

driver.get(url)

try:
    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "tr.lista__cabecalho"))
    )
    colunas = driver.find_elements(By.CSS_SELECTOR, "th.lista__titulo")

    dados = {}

    for coluna in colunas:
        categoria = coluna.text.strip()
        coluna.click()
        WebDriverWait(driver, 10).until(
            EC.presence_of_all_elements_located((By.CSS_SELECTOR, "tr.lista__indicador"))
        )

        elementos_listas = driver.find_elements(By.CSS_SELECTOR, "tr.lista__indicador")

        valores = []

        for elemento in elementos_listas:
            valor = elemento.find_element(By.CLASS_NAME, "lista__valor").text.strip()
            valores.append(valor)

        dados[categoria] = valores

    df = pd.DataFrame(dados)

finally:
    #pra vc buscar o valor específico passando posição
    valor_territorio = df.iloc[30]['TERRITÓRIO']
    print("O território é de: ",valor_territorio)

    valor_edu = df.iloc[13]['EDUCAÇÃO']
    print("Escolas: ",valor_edu)

    valor_pib = df.iloc[15]['ECONOMIA']
    print("Valor do PIB: ",valor_pib)

    driver.quit()