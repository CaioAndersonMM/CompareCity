# CompareCity

## Sobre o Projeto

CompareCity é um projeto desenvolvido para comparar indicadores socioeconômicos de cidades brasileiras, utilizando dados do site do IBGE (Instituto Brasileiro de Geografia e Estatística). Ele consiste em duas partes principais:

- Um script em Python que utiliza o Selenium para coletar dados do site do IBGE e salvar em um arquivo JSON.
- Uma aplicação web em Spring Boot que consome esses dados e permite visualizá-los de forma interativa.


## Raspagem de Dados do IBGE

A raspagem de dados do IBGE é uma abordagem útil para coletar informações socioeconômicas de diversas cidades brasileiras de forma automatizada. Neste projeto, a raspagem é realizada utilizando a biblioteca Selenium em Python para navegar no site do IBGE, extrair os dados das páginas relevantes e salvá-los em um arquivo JSON.

## Porquê a Raspagem?

- **Dados Abertos e Gratuitos**: O IBGE disponibiliza uma grande quantidade de dados abertos e gratuitos sobre as cidades brasileiras em seu site. Esses dados são uma fonte valiosa de informações para análises socioeconômicas. Porém não estão dispostas e tratadas facilmente como na página do IBGE CIDADES.

- **Atualização Automática**: Com a raspagem de dados, podemos automatizar o processo de coleta e manter as informações sempre atualizadas. Isso é crucial para garantir que as análises e comparações realizadas pela aplicação sejam baseadas em dados recentes e precisos.

- **Indicadores**: O IBGE fornece uma ampla gama de indicadores socioeconômicos para cada cidade, cobrindo áreas como demografia, economia, educação, saúde e meio ambiente. Isso permite uma análise abrangente e detalhada das características de cada município. Porém, fazer isso utilizando a API disponibilidade é trabalhoso, custoso e pesado. Coletando do IBGE CIDADES, os indicadores lá dispostos já são suficiente, relevantes e separados dos dados agregados vindos da API.

- **Escalabilidade e Flexibilidade**: A raspagem de dados do IBGE é escalável e flexível, o que significa que podemos adaptar facilmente o processo de coleta para incluir novos indicadores ou expandir a análise para mais cidades, conforme necessário.

- **Independência de Fontes Externas**: Ao raspar os dados diretamente do site do IBGE, reduzimos a dependência de fontes externas de dados. Isso nos dá mais controle sobre a qualidade e a confiabilidade das informações coletadas.

Em resumo, a raspagem de dados do IBGE foi uma escolha útil para o projeto CompareCity devido à disponibilidade de dados abertos e atualizados, à variedade de indicadores disponíveis e à flexibilidade e escalabilidade do processo de coleta. Isso nos permite criar uma aplicação robusta e informativa para comparar as características das cidades brasileiras.

## Papel do SpringBoot

1. **Integração com Bancos de Dados**: O Spring Boot oferecerá o suporte integrado para várias tecnologias de banco de dados, facilitando a integração do projeto com um banco de dados para armazenar e recuperar dados de forma eficiente.
2. **Recursos Web Avançados**: Também oferecerá suporte para a criação de APIs RESTful e o desenvolvimento de aplicativos web modernos. Com o Spring MVC e outras tecnologias, é possível criar interfaces de usuário dinâmicas e responsivas.


### Pré-requisitos

- Java JDK instalado
- Python 3.x instalado
- Chrome WebDriver
