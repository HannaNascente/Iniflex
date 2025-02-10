
# 👩‍💻Projeto Iniflex

O Iniflex é uma aplicação Java que demonstra o uso de princípios de programação orientada a objetos. O projeto inclui classes para gerenciar pessoas e funcionários, mostrando herança e encapsulamento.


## Funcionalidades

- Inserir todos os funcionários
- Remover o funcionário “João” da lista
- Imprimir todos os funcionários
- Aumentar salário dos funcionários em 10%
- Imprimir funcionários agrupados por função
- Imprimir funcionários que fazem aniversário nos meses 10 e 12
- Imprimir o funcionário com a maior idade
- Imprimir funcionários por ordem alfabética
- Imprimir o total dos salários dos funcionários
- Imprimir quantos salários mínimos ganha cada funcionário
## Estrutura do projeto

```
iniflex
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── iniflex
│   │   │           ├── App.java
│   │   │           ├── model
│   │   │           │   ├── Funcionario.java
│   │   │           │   └── Pessoa.java
│   │   │           └── service
│   │   │               ├── FuncionarioService.java
│   │   │               └── FuncionarioServiceImpl.java
│   │   └── resources
|   |       └── funcionarios.csv
├── pom.xml
└── README.md
```
## Visão Geral das Classes

- *App.java*: O ponto de entrada principal da aplicação. Contém métodos para iniciar a aplicação e gerenciar o fluxo principal.
- *Pessoa.java*: Representa uma pessoa com propriedades como nome e data de nascimento. Inclui métodos getter e setter para essas propriedades.
- *Funcionario.java*: Estende a classe Pessoa para representar um funcionário. Adiciona propriedades como função e salário, juntamente com seus respectivos métodos getter e setter.
- *FuncionarioService.java*: Interface que define os métodos para operações relacionadas aos funcionários.
- *FuncionarioServiceImpl.java*: Implementação da interface FuncionarioService, contendo a lógica para gerenciar a lista de funcionários e realizar operações como inserção, remoção, aumento de salário, entre outras.

## Tecnologias Utilizadas

- *Java 1.8*: Linguagem de programação utilizada para desenvolver a aplicação.
- *Maven*: Ferramenta de automação de compilação utilizada para gerenciar dependências e construir o projeto.
## Dependências

Este projeto utiliza o Maven para gerenciamento de dependências. Certifique-se de ter o Maven instalado em sua máquina para construir e executar o projeto.
## Autor

Este projeto foi desenvolvido por Hanna Karoline Nascente.
