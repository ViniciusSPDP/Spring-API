# Projeto Comércio

Este é um projeto de demonstração ("demo") para uma aplicação web desenvolvida com Spring Boot. Ele utiliza Spring Data JPA para persistência de dados com um banco de dados PostgreSQL.

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:
*   [Java 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior
*   [Maven](https://maven.apache.org/download.cgi)
*   Um banco de dados PostgreSQL em execução

## Configuração do Ambiente

Para que a aplicação se conecte ao banco de dados, é necessário criar e configurar o arquivo `application.properties`.

1.  Navegue até o diretório de recursos do projeto: `src/main/resources/`.
2.  Crie um novo arquivo chamado `application.properties`.
3.  Copie e cole o conteúdo abaixo no arquivo que você acabou de criar.

```properties
# Configurações da Aplicação
spring.application.name=comercio
server.port=8081

# Configurações da Conexão com o Banco de Dados (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/fatecaula2
spring.datasource.username=Usuario
spring.datasource.password=suasenha
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do JPA/Hibernate
# ddl-auto=update: atualiza o schema do banco de dados automaticamente ao iniciar a aplicação.
# Cuidado ao usar em produção!
spring.jpa.hibernate.ddl-auto=update

# Mostra os comandos SQL gerados pelo Hibernate no console
spring.jpa.show-sql=true

# Formata os comandos SQL exibidos para melhor legibilidade
spring.jpa.properties.hibernate.format_sql=true
```

**Atenção:** Lembre-se de alterar os valores de `spring.datasource.username` e `spring.datasource.password` para o usuário e senha corretos do seu banco de dados PostgreSQL. Certifique-se também de que o banco de dados `fatecaula2` exista.

## Como Executar o Projeto

1.  Abra seu terminal na pasta raiz do projeto.
2.  Execute o seguinte comando para iniciar a aplicação:

```bash
./mvnw spring-boot:run
```

Após a inicialização, a aplicação estará disponível em `http://localhost:8081`.
