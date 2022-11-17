CREATE TYPE category_type AS ENUM ('Aniversario', 'Formatura', 'Casamento');

create table categoria (
id SERIAL PRIMARY KEY,
descricao varchar(255) not null,
categoria category_type not null
);

create table evento (
id SERIAL PRIMARY KEY,
nome_evento varchar(255) not null,
categoria_id int not null,
data_evento date  not null, 
valor_custo_evento int not null, 
observacoes varchar(255) not null
)

INSERT INTO categoria VALUES (
default, 'utilizada para aniversario', 'Aniversario');

INSERT INTO categoria VALUES (
default, 'utilizada para Formatura', 'Formatura');

INSERT INTO categoria VALUES (
default, 'utilizada para Casamento', 'Casamento');