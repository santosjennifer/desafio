# Objetivo

Desafio de automação de testes de API seguindo as regras do projeto [desafios-qa-automacao](https://github.com/desafios-qa-automacao/desafio-sicredi).

Para criação dos testes foi utilizado o Eclipse IDE, projeto com estrutura Maven.

Testes implementados em Java utilizado JUnit e Rest Assured.

Utilizado o Postman para auxilio nos testes e validações manuais.

**Observação:** No documento _Status dos Testes.pdf_ tem detalhes dos testes executados e no arquivo _Orientações para execução do Desafio_v1.pdf_ tem as regras.

## A estrutura do projeto de testes é dividida em: 

* ***Core***: Onde estão as configurações essenciais de acesso a API

* ***Suites***: Onde pode ser executado todos os testes ou também por módulos

* ***Test***: Onde estão os testes, subdividido em dois pacotes: 
  * _Restricões_: Testes de restrições de CPF
  * _Simulações_: Testes de simulações
 
## Tecnologia utilizada 

- Java 8
- Java JDK 11
- Maven - Apache Maven 3.8.2
- Eclipse IDE - 2021-06 (4.20.0)
- Postman - 8.11.1
- JUnit 4
- Rest Assured 4
