
CREATE TABLE dbo.Cliente
(
    id INT NOT NULL PRIMARY KEY IDENTITY(1, 1),
    nome [VARCHAR](255) NOT NULL,
    email [VARCHAR](255) NOT NULL,
    senha [VARCHAR](255) NOT NULL,
    rua [VARCHAR](255) NOT NULL,
    bairro [VARCHAR](255) NOT NULL,
    cep [VARCHAR](255) NOT NULL,
    cidade [VARCHAR](255) NOT NULL,
    estado [VARCHAR](255) NOT NULL
);
GO

CREATE TABLE dbo.Categoria
(
    id INT NOT NULL PRIMARY KEY IDENTITY(1, 1),
    nome [VARCHAR](255) NOT NULL,
);
GO

CREATE TABLE dbo.Produto
(
    id INT NOT NULL PRIMARY KEY IDENTITY(1, 1),
    nome [VARCHAR](255) NOT NULL,
    descricao [VARCHAR](255) NOT NULL,
    foto [VARCHAR](255) NOT NULL,
    quantidade INT NOT NULL,
    preco numeric(19, 2) NOT NULL,
    categoria_id INT  NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);

CREATE TABLE dbo.Pedido
(
    id INT NOT NULL PRIMARY KEY IDENTITY(1, 1),
    status_Pedido INT NOT NULL,
    data_pedido [DATETIME] NOT NULL,
    sessao [VARCHAR](255) NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);
GO

CREATE TABLE dbo.Item_Pedido
(
    Id INT NOT NULL PRIMARY KEY IDENTITY(1, 1),
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    valor numeric(19, 2) NOT NULL,
    sub_total numeric(19, 2) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES Produto (id)
);
GO

CREATE TABLE dbo.Pedido_Itens_Pedido(
    pedido_id INT NOT NULL,
    itens_pedido_id INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedido (id),
    FOREIGN KEY (itens_pedido_id) REFERENCES Item_Pedido (id)
)

