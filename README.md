# DistanciaAgenciasAPI

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)

## 1. Sobre o Projeto

Esta é uma API REST para cadastrar e consultar a distância de agências bancárias. O foco principal foi construir uma aplicação limpa e de fácil manutenção, usando padrões de design profissionais como a Arquitetura Hexagonal e os princípios SOLID.

## 2. Design e Arquitetura

A arquitetura do projeto foi pensada para isolar a lógica de negócio de detalhes de tecnologia (frameworks, banco de dados, etc.).

- **Arquitetura Hexagonal (Portas e Adaptadores):** O núcleo da aplicação (domínio e serviços) é completamente independente. Ele se comunica com o mundo exterior através de **Portas** (interfaces). As tecnologias, como o Controller da Web e a persistência com JPA, são **Adaptadores** que se conectam a essas portas.

- **Benefício Prático:** Essa separação torna o núcleo de negócio 100% testável de forma isolada e permite trocar tecnologias (ex: mover de H2 para PostgreSQL) sem impactar as regras de negócio.

- **Princípios SOLID:** A estrutura se apoia em princípios como a **Responsabilidade Única** (cada classe tem uma função clara) e a **Inversão de Dependência** (o núcleo define os contratos, e os detalhes de tecnologia se adaptam a ele).

## 3. Tecnologias

- Java 17 & Spring Boot 3.3
- Spring Data JPA & H2 Database
- Maven
- JUnit 5 & Mockito para testes

## 4. Como Executar

**Pré-requisitos:**
- JDK 17
- Maven

**Passos:**

1. Clone o repositório: `git clone https://github.com/wesfrs/DistanciaAgenciasApiApplication.git`
2. No terminal, navegue até a raiz do projeto.
3. Execute `mvn spring-boot:run`.

A API estará disponível em `http://localhost:8080`.

## 5. Testes

O projeto tem cobertura de testes unitários e de integração para garantir a qualidade do código. Para rodá-los, execute:

```bash
   mvn test
```

## 6. Endpoints da API

### Cadastrar Agência

- `POST /desafio/cadastrar`

**Body da Requisição:**
```json
{
  "nome": "Agencia Central",
  "posX": -46.6333,
  "posY": -23.5505
}
```

**Resposta de Sucesso (201 Created):**
```json
{
  "id": 1,
  "name": "Agencia Central"
}
```

### Calcular Distâncias

- `GET /desafio/distancia?posX={x}&posY={y}`

**Exemplo de Requisição:**
```bash
curl -X GET "http://localhost:8080/desafio/distancia?posX=-46.63&posY=-23.55"
```

**Resposta de Sucesso (200 OK):**
```json
[
    {
        "nomeAgencia": "Agencia Central",
        "distancia": 0.0038
    }
]
```

## 7. Próximos Passos (Melhorias)

- **Performance:** Implementar **paginação** na consulta de agências para garantir a escalabilidade.
- **Documentação:** Adicionar **Swagger/OpenAPI** para gerar uma documentação interativa.

---
*Desenvolvido por Wesley Freire Silva*
