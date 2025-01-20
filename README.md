# Projeto Consulta de CEP API

## Descrição
Este projeto foi desenvolvido como parte de um processo seletivo. O objetivo é criar uma interface web que permite a consulta de CEPs. Os dados retornados pela API (externa) são validados e, dependendo do resultado, o usuário é direcionado para uma página específica que exibe as informações do endereço ou uma mensagem de erro. 

[Atualização][15-01-2025] <br>
Agora, com uma requisição bem-sucedida, o CEP consultado é registrado em um banco de dados MySQL contido em um contêiner Docker. Os CEPs registrados podem ser visualizados em uma página separada.

## Tecnologias Utilizadas
- **Spring Boot**: Framework para criar aplicações Java.
- **JUnit 5**: Framework de testes para Java.
- **Spring Boot Test**: Suporte de teste para Spring Boot.
- **Mockito**: Framework para criação de mocks (usado em testes unitários).
- **Thymeleaf**: Motor de template para renderização de páginas HTML.
- **Bootstrap**: Biblioteca de front-end para criação de interfaces de usuário responsivas.
- **Docker**: Plataforma para criar, implantar e executar contêineres.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.

## Estrutura do Projeto
- **Controller**: Lida com as requisições HTTP.
- **Service**: Contém a lógica de negócios.
- **Model**: Representa os dados da aplicação.
- **Repository**: Interface para operações de banco de dados.
- **Templates**: Contém os arquivos HTML para a interface do usuário.
- **Testes**: Inclui testes unitários e de integração.

## Funcionalidades
- **Consulta de CEP**: Consulta um CEP usando uma API externa e exibe as informações do endereço.
- **Registro de CEP**: Registra o CEP consultado com sucesso em um banco de dados MySQL.
- **Visualização de CEPs**: Exibe uma lista de todos os CEPs registrados em uma página separada.

## Testes
- **Testes Unitários**
- **Testes de Integração**

## Como Executar o Projeto

1. **Clone o repositório e acesse o projeto**:
```
git clone https://github.com/AndreasBorba/consulta-cep.git
```
```
cd projetoAPI
```
2. **Inicie o contêiner Docker com o MySQL:**:
```
docker-compose up
```
3. **Execute a aplicação Spring Boot:**:
```
mvn spring-boot:run
```
4. **Acesse a aplicação:**:
- http://localhost:8081

5. **Executando os Testes:**
```
mvn test
```
```
mvn -Dtest=nomeClasse#nomeMetodo test
```



