CREATE TABLE if not exists estoque (
                         estoque_id SERIAL PRIMARY KEY,
                         produto_id INT NOT NULL,
                         quantidade INT NOT NULL CHECK (quantidade >= 0),
                         localizacao_id INT NOT NULL,
                         FOREIGN KEY (produto_id) REFERENCES produtos (produto_id),
                         FOREIGN KEY (localizacao_id) REFERENCES localizacao (localizacao_id)
);
