# 🏢 ERP - Sistema de Gestão Empresarial

API REST robusta desenvolvida com Java e Spring Boot, aplicando **Domain Driven Design (DDD)** e **Arquitetura Hexagonal** na prática.

> Projeto desenvolvido como portfólio para demonstrar conhecimento em arquitetura de software, boas práticas e tecnologias do ecossistema Java.

---

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4**
- **Spring Security + JWT** — autenticação stateless
- **Spring Data JPA + Hibernate** — persistência
- **MySQL 8** — banco de dados
- **Flyway** — migrations versionadas
- **Docker + Docker Compose** — containerização
- **JUnit 5** — testes unitários e de integração

---

## 🏗️ Arquitetura

O projeto segue **DDD com Arquitetura Hexagonal**, organizado por contextos de negócio, não por camada técnica.

```
src/
└── com.empresa.erp/
    ├── vendas/
    │   ├── domain/        → regras de negócio
    │   ├── application/   → casos de uso
    │   └── infrastructure → controllers, repositórios JPA
    ├── clientes/
    ├── produtos/
    ├── usuarios/
    ├── shared/            → exceções e eventos base
    └── config/            → segurança e JWT
```

### Conceitos aplicados
- **Bounded Contexts** — cada contexto tem seu próprio modelo
- **Aggregate Root** — `Venda` controla seus `ItemVenda`
- **Value Objects** — `Dinheiro`, `CPF`, `Endereco`
- **Domain Events** — `VendaRealizadaEvent`, `ClienteCadastradoEvent`
- **Repository Pattern** — interfaces no domínio, implementações na infraestrutura

---

## 📦 Funcionalidades

### Autenticação
- Cadastro de usuários com perfis (ADMIN, OPERADOR, VISUALIZADOR)
- Login com geração de token JWT
- Proteção de rotas por token

### Clientes
- CRUD completo
- Validação de CPF e endereço como Value Objects

### Produtos
- CRUD completo
- Controle de estoque com validação de quantidade

### Vendas
- Abertura de venda vinculada a um cliente real
- Adição de itens com dedução automática de estoque
- Fechamento de venda com validação de regras de negócio

---

## ▶️ Como rodar

### Pré-requisitos
- Docker e Docker Compose instalados

### Com Docker (recomendado)

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/erp.git
cd erp
```

2. Crie o arquivo `.env` na raiz: 

   MYSQL_ROOT_PASSWORD=sua_senha
   SPRING_DATASOURCE_PASSWORD=sua_senha
   JWT_SECRET=sua_chave_secreta_minimo_32_caracteres
   JWT_EXPIRATION=86400000

3. Configure o `application.properties`:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

4. Gere o JAR:
```bash
./mvnw clean package -DskipTests
```

5. Suba os containers:
```bash
docker-compose up --build
```

A API estará disponível em `http://localhost:8080`

---

## 🔐 Autenticação

Todas as rotas exceto `/usuarios/cadastrar` e `/usuarios/login` requerem token JWT.

**Login:**
```http
POST /usuarios/login
Content-Type: application/json

{
    "email": "seu@email.com",
    "senha": "suasenha"
}
```

**Usar o token:**

Authorization: Bearer {token}

---

## 📋 Endpoints

| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| POST | /usuarios/cadastrar | Cadastrar usuário | ❌ |
| POST | /usuarios/login | Login | ❌ |
| GET | /usuarios | Listar usuários | ✅ |
| GET | /clientes | Listar clientes | ✅ |
| POST | /clientes | Cadastrar cliente | ✅ |
| GET | /clientes/{id} | Buscar cliente | ✅ |
| PUT | /clientes/{id} | Atualizar cliente | ✅ |
| DELETE | /clientes/{id} | Deletar cliente | ✅ |
| GET | /produtos | Listar produtos | ✅ |
| POST | /produtos | Cadastrar produto | ✅ |
| GET | /produtos/{id} | Buscar produto | ✅ |
| PUT | /produtos/{id} | Atualizar produto | ✅ |
| DELETE | /produtos/{id} | Deletar produto | ✅ |
| POST | /vendas/abrir/{clienteId} | Abrir venda | ✅ |
| POST | /vendas/{id}/itens | Adicionar item | ✅ |
| POST | /vendas/{id}/fechar | Fechar venda | ✅ |

---

## 🧪 Testes

```bash
./mvnw test
```

- Testes unitários do domínio — `VendaTest`, `ProdutoTest`, `ClienteTest`
- Testes de integração — `VendaControllerTest`

---

## 👨‍💻 Autor

Feito por **Wanderson Jafé** — [LinkedIn](https://linkedin.com/in/wandersonjafe)