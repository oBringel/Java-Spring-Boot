
CREATE TABLE pedidos (
    id BIGINT GENERATION ALWAYS AS IDENTITY PRIMARY KEY,
    quantidade INT,
    id_produto BIGINT,
    local_date_time TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE'
);

CREATE TABLE estoque (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(50) NOT NULL,
    produto_id BIGINT NOT NULL,
    local_date_time TIMESTAMP,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id)
        REFERENCES produtos (id)
);