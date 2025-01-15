# Projeto Consulta de CEP API

## Descrição
Este projeto foi desenvolvido como parte de um processo seletivo. O objetivo é criar uma interface web que consome uma API externa para consulta de CEP. Os dados retornados pela API são validados e, dependendo do resultado, o usuário é direcionado para uma página específica que exibe as informações do endereço ou uma mensagem de erro. Este projeto foi desenvolvido como parte de um processo seletivo. O objetivo é criar uma interface web que consome uma API externa para consulta de CEP. Os dados retornados pela API são validados e, dependendo do resultado, o usuário é direcionado para uma página específica que exibe as informações do endereço ou uma mensagem de erro. 

[Atualização][15-01-2025]
Agora, com uma requisição bem-sucedida, o CEP consultado é registrado em um banco de dados MySQL contido em um contêiner Docker. Os CEPs registrados podem ser visualizados em uma página separada.

## Tecnologias Utilizadas
- **Spring Boot**: Framework para criar aplicações Java.
- **JUnit 5**: Framework de testes para Java.
- **Spring Boot Test**: Suporte de teste para Spring Boot.
- **Mockito**: Framework para criação de mocks (usado em testes unitários).
- **Thymeleaf**: Motor de template para renderização de páginas HTML.
- **Bootstrap**: Biblioteca de front-end para criação de interfaces de usuário responsivas.

## Estrutura do Projeto
- **Controller**: Lida com as requisições HTTP.
- **Service**: Contém a lógica de negócios.
- **Model**: Representa os dados da aplicação.
- **Templates**: Contém os arquivos HTML para a interface do usuário.
- **Testes**: Inclui testes unitários e de integração.

## Testes
- **Testes Unitários**
- **Testes de Integração**