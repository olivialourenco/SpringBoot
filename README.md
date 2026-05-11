# PrimeiroSpringTA - Projeto educacional

Aplicação web didática construída com Spring Boot, Thymeleaf e MySQL para demonstrar um fluxo simples de cadastro e consulta de dados em banco relacional.

Projeto educacional.

## Objetivo da aplicação

O projeto implementa uma interface web básica com formulários para:

- cadastrar nome e e-mail em uma tabela de alunos;
- consultar um registro por código;
- renderizar respostas no servidor usando Thymeleaf.

## Principais funcionalidades

- Página inicial estática (`index.html`) com acesso aos fluxos da aplicação.
- Formulário de cadastro (`/form`) com persistência no MySQL (`POST /respostaform`).
- Formulário de consulta por código (`/banco`) com busca no MySQL (`POST /bancoconecta`).
- Renderização de respostas em páginas HTML do lado servidor.

## Arquitetura e tecnologias

### Stack

- Java 17
- Spring Boot 3.3.4
- Spring MVC (`spring-boot-starter-web`)
- Thymeleaf (`spring-boot-starter-thymeleaf`)
- MySQL Connector/J
- Maven (com Maven Wrapper)

### Organização do projeto

```text
SpringBoot/
  README.md
  PrimeiroSpringTA/
    pom.xml
    mvnw / mvnw.cmd
    src/
      main/
        java/pro/PrimeiroSpringTA/
          PrimeiroSpringTaApplication.java
          WebController.java
          conectar.java
        resources/
          application.properties
          static/
            index.html
            teste.css
          templates/
            form.html
            respostaform.html
            banco.html
            bancoconecta.html
      test/
        java/pro/PrimeiroSpringTA/
          PrimeiroSpringTaApplicationTests.java
```

### Visão técnica

- A aplicação segue o modelo MVC com rotas em `WebController`.
- A camada de dados é feita via JDBC manual na classe `conectar` (sem Spring Data/JPA).
- O banco esperado é MySQL local com schema `teste`.

## Como executar localmente

### 1) Pré-requisitos

- JDK 17 instalado e configurado no `PATH`
- MySQL em execução na máquina local
- Porta `3306` disponível
- Maven (opcional, pois o projeto já inclui Maven Wrapper)

### 2) Banco de dados

Crie o banco `teste` e as tabelas utilizadas pela aplicação:

- `aluno` (utilizada para cadastro e consulta);
- `log` (utilizada por métodos auxiliares da classe de conexão).

Exemplo mínimo de tabela principal:

```sql
CREATE DATABASE IF NOT EXISTS teste;
USE teste;

CREATE TABLE IF NOT EXISTS aluno (
  cod INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(180) NOT NULL,
  codcidade INT NOT NULL
);
```

### 3) Ajuste de conexão (recomendado)

Atualmente a conexão com MySQL está definida diretamente no código (`conectar.java`), incluindo host, usuário e senha.  
Para uso local, ajuste esses valores conforme seu ambiente antes de iniciar.

### 4) Executar a aplicação

No diretório `PrimeiroSpringTA`:

```bash
# Windows (PowerShell/CMD)
.\mvnw.cmd spring-boot:run
```

ou:

```bash
# Build + execução via JAR
.\mvnw.cmd clean package
java -jar target/PrimeiroSpringTA-0.0.1-SNAPSHOT.jar
```

### 5) Acessar no navegador

- Home: [http://localhost:8080/index.html](http://localhost:8080/index.html)
- Cadastro: [http://localhost:8080/form](http://localhost:8080/form)
- Consulta: [http://localhost:8080/banco](http://localhost:8080/banco)

## Testes

O projeto contém dependência de testes (`spring-boot-starter-test`), porém a suíte atual está mínima/incompleta.  
Para executar testes existentes:

```bash
.\mvnw.cmd test
```

## Segurança e boas práticas

- Não versione credenciais, tokens ou chaves de API no repositório.
- Evite manter usuário/senha de banco hardcoded em código-fonte.
- Prefira externalizar configuração sensível por variáveis de ambiente ou arquivos locais não versionados.
- Evite registrar dados sensíveis em logs (ex.: senhas em texto claro).
- Revise permissões e credenciais antes de publicar o projeto.

## Melhorias recomendadas

- Migrar conexão JDBC manual para configuração Spring (`spring.datasource.*`) e pool de conexões.
- Remover dependências não utilizadas.
- Adicionar migrações de banco (Flyway/Liquibase).
- Expandir cobertura de testes automatizados.
- Remover artefatos compilados versionados (como `target/`), quando aplicável.
