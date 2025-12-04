CREATE TABLE pedidos (
    id BIGINT GENERATION ALWAYS AS IDENTITY PRIMARY KEY,
    quantidade INT,
    id_produto BIGINT,
    nome VARCHAR()
)