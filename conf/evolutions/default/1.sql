# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions
 
# --- !Ups
 
CREATE TABLE pessoa (
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL,
  tipo_pessoa               VARCHAR(1),
  numero_documento          VARCHAR(255) NOT NULL
);

CREATE TABLE veiculo (
  id                        SERIAL PRIMARY KEY,
  placa                     VARCHAR(255) NOT NULL,
  capacidade                NUMBER
);
 
# --- !Downs
 
DROP TABLE IF EXISTS pessoa;

DROP TABLE IF EXISTS veiculo;