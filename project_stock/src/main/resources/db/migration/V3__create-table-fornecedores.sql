CREATE TABLE if not exists fornecedores (
                              fornecedor_id SERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              contato VARCHAR(255)
);
