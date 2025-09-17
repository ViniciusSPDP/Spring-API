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

## Documentação da API

Base URL: `http://localhost:8081`

- Headers comuns:
  - `Content-Type: application/json`
  - `Accept: application/json`

Os endpoints abaixo seguem um padrão CRUD simples. Os métodos `POST` e `PUT` recebem JSON no corpo. Os IDs são gerados automaticamente (não envie no `POST`). As respostas de criação/atualização/remoção retornam mensagens de texto com status HTTP 200.

### Bairros

- Listar bairros
  - Método: `GET`
  - Caminho: `/bairros`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codbairro": 1, "nomebairro": "Centro" }
    ]
    ```
- Criar bairro
  - Método: `POST`
  - Caminho: `/bairros`
  - Body (exemplo):
    ```json
    { "nomebairro": "Centro" }
    ```
  - Resposta 200: `"Bairro salvo com sucesso!"`
- Atualizar bairro
  - Método: `PUT`
  - Caminho: `/bairros/{id}`
  - Body (exemplo):
    ```json
    { "nomebairro": "Centro Expandido" }
    ```
  - Resposta 200: `"Bairro com id {id} editado com sucesso!"`
- Remover bairro
  - Método: `DELETE`
  - Caminho: `/bairros/{id}`
  - Resposta 200: `"Bairro com id {id} apagado com sucesso!"`

Exemplos `curl`:
```bash
curl -s http://localhost:8081/bairros
curl -s -X POST http://localhost:8081/bairros \
  -H 'Content-Type: application/json' \
  -d '{"nomebairro":"Centro"}'
curl -s -X PUT http://localhost:8081/bairros/1 \
  -H 'Content-Type: application/json' \
  -d '{"nomebairro":"Centro Novo"}'
curl -s -X DELETE http://localhost:8081/bairros/1
```

### Sexos

- Listar sexos
  - Método: `GET`
  - Caminho: `/sexos`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codsexo": 1, "nomesexo": "Masculino" }
    ]
    ```
- Criar sexo
  - Método: `POST`
  - Caminho: `/sexos`
  - Body (exemplo):
    ```json
    { "nomesexo": "Masculino" }
    ```
  - Resposta 200: `"Sexo salvo com sucesso!"`
- Atualizar sexo
  - Método: `PUT`
  - Caminho: `/sexos/{id}`
  - Body (exemplo):
    ```json
    { "nomesexo": "Feminino" }
    ```
  - Resposta 200: `"Sexo com id {id} editado com sucesso!"`
- Remover sexo
  - Método: `DELETE`
  - Caminho: `/sexos/{id}`
  - Resposta 200: `"Sexo com id {id} apagado com sucesso!"`

### Ceps

- Listar ceps
  - Método: `GET`
  - Caminho: `/ceps`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codcep": 1, "numerocep": "01001-000" }
    ]
    ```
- Criar cep
  - Método: `POST`
  - Caminho: `/ceps`
  - Body (exemplo):
    ```json
    { "numerocep": "01001-000" }
    ```
  - Resposta 200: `"Cep salvo com sucesso!"`
- Atualizar cep
  - Método: `PUT`
  - Caminho: `/ceps/{id}`
  - Body (exemplo):
    ```json
    { "numerocep": "01002-000" }
    ```
  - Resposta 200: `"Cep com id {id} editado com sucesso!"`
- Remover cep
  - Método: `DELETE`
  - Caminho: `/ceps/{id}`
  - Resposta 200: `"Cep com id {id} apagado com sucesso!"`

### Ruas

- Listar ruas
  - Método: `GET`
  - Caminho: `/ruas`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codrua": 1, "nomerua": "Av. Paulista" }
    ]
    ```
- Criar rua
  - Método: `POST`
  - Caminho: `/ruas`
  - Body (exemplo):
    ```json
    { "nomerua": "Av. Paulista" }
    ```
  - Resposta 200: `"Rua salva com sucesso!"`
- Atualizar rua
  - Método: `PUT`
  - Caminho: `/ruas/{id}`
  - Body (exemplo):
    ```json
    { "nomerua": "Rua Augusta" }
    ```
  - Resposta 200: `"Rua com id {id} editada com sucesso!"`
- Remover rua
  - Método: `DELETE`
  - Caminho: `/ruas/{id}`
  - Resposta 200: `"Rua com id {id} apagada com sucesso!"`

### UFs

- Listar UFs
  - Método: `GET`
  - Caminho: `/ufs`
  - Resposta 200 (exemplo):
    ```json
    [
      { "coduf": 1, "nomeuf": "São Paulo", "siglauf": "SP" }
    ]
    ```
- Criar UF
  - Método: `POST`
  - Caminho: `/ufs`
  - Body (exemplo):
    ```json
    { "nomeuf": "São Paulo", "siglauf": "SP" }
    ```
  - Resposta 200: `"UF salvo com sucesso!"`
- Atualizar UF
  - Método: `PUT`
  - Caminho: `/ufs/{id}`
  - Body (exemplo):
    ```json
    { "nomeuf": "Rio de Janeiro", "siglauf": "RJ" }
    ```
  - Resposta 200: `"Sexo com id {id} editado com sucesso!"` (mensagem do código)
- Remover UF
  - Método: `DELETE`
  - Caminho: `/ufs/{id}`
  - Resposta 200: `"UF com id {id} apagado com sucesso!"`

### Tipos

- Listar tipos
  - Método: `GET`
  - Caminho: `/tipos`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codtipo": 1, "nometipo": "Eletrônico" }
    ]
    ```
- Criar tipo
  - Método: `POST`
  - Caminho: `/tipos`
  - Body (exemplo):
    ```json
    { "nometipo": "Eletrônico" }
    ```
  - Resposta 200: `"Tipo salvo com sucesso!"`
- Atualizar tipo
  - Método: `PUT`
  - Caminho: `/tipos/{id}`
  - Body (exemplo):
    ```json
    { "nometipo": "Vestuário" }
    ```
  - Resposta 200: `"Tipo com id {id} editado com sucesso!"`
- Remover tipo
  - Método: `DELETE`
  - Caminho: `/tipos/{id}`
  - Resposta 200: `"Tipo com id {id} apagado com sucesso!"`

### Marcas

- Listar marcas
  - Método: `GET`
  - Caminho: `/marcas`
  - Resposta 200 (exemplo):
    ```json
    [
      { "codmarca": 1, "nomemarca": "Acme" }
    ]
    ```
- Criar marca
  - Método: `POST`
  - Caminho: `/marcas`
  - Body (exemplo):
    ```json
    { "nomemarca": "Acme" }
    ```
  - Resposta 200: `"Marca salva com sucesso!"`
- Atualizar marca
  - Método: `PUT`
  - Caminho: `/marcas/{id}`
  - Body (exemplo):
    ```json
    { "nomemarca": "Globex" }
    ```
  - Resposta 200: `"Marca com id {id} editada com sucesso!"`
- Remover marca
  - Método: `DELETE`
  - Caminho: `/marcas/{id}`
  - Resposta 200: `"Marca com id {id} apagada com sucesso!"`

### Exemplo

- Mensagem de boas-vindas
  - Método: `GET`
  - Caminho: `/exemplo`
  - Resposta 200 (texto): `"Bem vindo ao metodo get"`
- Apagar (exemplo)
  - Método: `DELETE`
  - Caminho: `/exemplo/{id}`
  - Resposta 200 (texto): `"Voce quer apagar o id {id}"`

## Observações Gerais

- Campos `cod*` são gerados automaticamente pela aplicação (estratégia `SEQUENCE`).
- Os exemplos de resposta exibem o formato básico esperado; o conteúdo depende dos dados persistidos.
- Status HTTP retornado para `POST`, `PUT` e `DELETE` é 200 com mensagem de texto (conforme implementação atual dos controllers).
