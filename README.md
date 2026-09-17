# Trabalho de Aplicações para Internet.

Sistema de gerenciamento de tarefas (To-Do) desenvolvido em Java, aplicando o padrão de arquitetura **MVC** (Model-View-Controller), complementado pelos padrões de projeto **DAO** (Data Access Object) e **Service Layer**.


## Funcionalidades

- Criar tarefa (título e descrição)
- Listar todas as tarefas cadastradas
- Editar uma tarefa existente
- Marcar/desmarcar tarefa como concluída
- Excluir tarefa

## Tecnologias utilizadas

- Java 17 -> linguagem principal
- Jakarta Servlet / JSP -> camada web (Controller e View)
- JSTL -> tags para lógica simples dentro das JSPs
- Maven -> gerenciamento de dependências e build
- Apache Tomcat 10.1 -> servidor de aplicação
- MySQL 8.4 -> banco de dados
- Docker / Docker Compose -> containerização do ambiente (MySQL + Tomcat)
- Git / GitHub -> versionamento de código

## Arquitetura

O projeto segue o padrão **MVC**, complementado por duas camadas adicionais (DAO e Service), organizadas da seguinte forma:

```
src/main/java/br/com/uniube/todo/
├── model/       -> Tarefa.java (representa os dados de uma tarefa)
├── config/      -> ConexaoBD.java (conexão com o MySQL, padrão Singleton)
├── dao/         -> TarefaDAO.java (acesso ao banco de dados / SQL)
├── service/     -> TarefaService.java (regras de negócio e validações)
└── controller/  -> TarefaServlet.java (recebe requisições HTTP e decide a view)

src/main/webapp/
├── css/         -> style.css
└── WEB-INF/
    └── views/   -> lista.jsp, form.jsp (telas do sistema)
```

**Fluxo de uma requisição:**

```
Navegador -> Tomcat -> TarefaServlet (Controller) -> TarefaService -> TarefaDAO -> MySQL
                                                                         |
Navegador <- lista.jsp/form.jsp (View) <----------------------------------
```

### Padrões de projeto aplicados

- **MVC** — separa dados (Model), interface (View) e controle de fluxo (Controller)
- **DAO** — isola todo o acesso a dados numa camada própria (`TarefaDAO`)
- **Service Layer** — concentra as regras de negócio e validações (`TarefaService`)
- **Singleton** — garante uma única instância de conexão com o banco (`ConexaoBD`)

## Como rodar o projeto

### Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado
- [Java 17+](https://www.oracle.com/java/technologies/downloads/) instalado
- [Maven](https://maven.apache.org/) instalado (ou usar o wrapper, se disponível)
- [Git](https://git-scm.com/) instalado

### Passo a passo

git clone <URL_DO_REPOSITORIO>
cd projetoweb_patrik
docker compose up -d
mvn clean package
docker compose down
docker compose up -d

Depois acesse no navegador:

http://localhost:8080/projetoweb_patrik/

Para encerrar o ambiente:

docker compose down

## Estrutura do banco de dados

Tabela `tarefas`:

- id (BIGINT, chave primária, auto increment) -> identificador único
- titulo (VARCHAR(150)) -> título da tarefa (obrigatório)
- descricao (VARCHAR(500)) -> descrição da tarefa
- concluida (BOOLEAN) -> status de conclusão
- data_criacao (DATE) -> data em que a tarefa foi criada