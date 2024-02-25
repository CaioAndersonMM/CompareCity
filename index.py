from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# Inicializando o driver do navegador
driver = webdriver.Chrome()  # ou o navegador de sua preferência

# Definindo uma espera implícita de 10 segundos
driver.implicitly_wait(10)

def clicar_proximo_cabecalho(indice):
    try:
        # Encontrar todos os elementos de cabeçalho
        cabecalhos = driver.find_elements(By.CSS_SELECTOR, "tr.lista__cabecalho")

        # Se o índice estiver dentro do intervalo válido, clique no próximo cabeçalho
        if indice < len(cabecalhos):
            cabecalhos[indice].click()
            return True
        else:
            return False  # Não há mais cabeçalhos para clicar

    except Exception as e:
        print(f"Erro ao clicar no próximo cabeçalho: {e}")
        return False

try:
    # Abrindo a página desejada
    driver.get("https://cidades.ibge.gov.br/brasil/rn/jucurutu/panorama")

    # Inicializando o índice do cabeçalho
    indice_cabecalho = 0

    # Loop para clicar em mais cabeçalhos (máximo de 6 vezes)
    for _ in range(6):
        # Esperar até que pelo menos uma linha da tabela esteja presente na página
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "//section[@id='dados']//tr"))
        )

        # Encontrar todas as células (td) dentro das linhas da tabela
        celulas = driver.find_elements(By.XPATH, "//section[@id='dados']//tr/td")
        textos_celulas = []

        # Iterar sobre as células e adicionar o texto não vazio à lista
        for celula in celulas:
            if celula.text.strip():  # Ignorar células vazias
                textos_celulas.append(celula.text)

        # Juntar os textos das células em uma única string
        texto_completo = "\n".join(textos_celulas)

        # Imprimir o texto completo
        print(texto_completo.encode('utf-8').decode('utf-8'))

        # Atualizar o índice do cabeçalho
        indice_cabecalho += 1

        # Clicar no próximo cabeçalho
        if not clicar_proximo_cabecalho(indice_cabecalho):
            break  # Se não houver mais cabeçalhos, saia do loop

finally:
    # Fechando o navegador quando terminar
    driver.quit()
