CREATE TABLE if not exists produtos (
                          produto_id SERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          tipo_categoria varchar(50) not null check(tipo_categoria in('Eletrodomesticos', 'Culinaria')),
                          unidade_medida VARCHAR(50)
);
