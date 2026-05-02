-- Tabela de Usuários
CREATE TABLE usuarios (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL
);

-- Tabela de Clientes (com campos do EnderecoEmbeddable embutidos)
CREATE TABLE clientes (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    logradouro VARCHAR(255),
    numero VARCHAR(20),
    cep VARCHAR(10),
    cidade VARCHAR(100),
    estado VARCHAR(2)
);

-- Tabela de Produtos
CREATE TABLE produtos (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(19, 2) NOT NULL,
    estoque INT NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

-- Tabela de Vendas
CREATE TABLE vendas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    cliente_id BINARY(16) NOT NULL,
    status VARCHAR(50) NOT NULL,
    total DECIMAL(19, 2) NOT NULL
);

-- Tabela de Itens da Venda (relacionada com Venda via venda_id)
CREATE TABLE itens_venda (
    id BINARY(16) NOT NULL PRIMARY KEY,
    venda_id BINARY(16),
    produto_id BINARY(16) NOT NULL,
    nome_produto VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(19, 2) NOT NULL,
    CONSTRAINT fk_itens_venda_venda FOREIGN KEY (venda_id) REFERENCES vendas (id)
);