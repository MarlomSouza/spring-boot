
CREATE TABLE dbo.Cliente
(
    id INT NOT NULL PRIMARY KEY,
    nome [VARCHAR](255) NOT NULL,
    email [VARCHAR](255) NOT NULL,
    senha [VARCHAR](255) NOT NULL,
    bairro [VARCHAR](255) NOT NULL,
    cep [VARCHAR](255) NOT NULL,
    cidade [VARCHAR](255) NOT NULL,
    estado [VARCHAR](255) NOT NULL
);
GO

CREATE TABLE dbo.Pedido
(
    id INT NOT NULL PRIMARY KEY,
    status INT NOT NULL,
    data [TIMESTAMP] NOT NULL,
    sessao [VARCHAR](255) not null,
    cliente_id INT not null,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);
GO

CREATE TABLE dbo.Categoria
(
    id INT NOT NULL PRIMARY KEY,
    nome [VARCHAR](255) NOT NULL,
);
GO

CREATE TABLE dbo.Produto
(
    id INT NOT NULL PRIMARY KEY,
    nome [VARCHAR](255) NOT NULL,
    descricao [VARCHAR](255) NOT NULL,
    foto [VARCHAR](255) NOT NULL,
    quantidade INT NOT NULL,
    preco numeric(19, 2) NOT NULL
);
GO

CREATE TABLE dbo.ItemPedido
(
    Id INT NOT NULL PRIMARY KEY,
    pedido_id INT not null,
    produto_id INT not null,
    quantidade INT NOT NULL,
    valor numeric(19, 2) NOT NULL,
    subtotal numeric(19, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedido (id),
    FOREIGN KEY (produto_id) REFERENCES Produto (id)
);
GO
