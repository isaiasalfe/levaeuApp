# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions
 
# --- !Ups
 
CREATE TABLE pessoa (
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL,
  tipo_pessoa               CHAR(1),
  numero_documento          VARCHAR(255)
);

CREATE TABLE passageiro (
	id 						INT PRIMARY KEY REFERENCES pessoa(id),
	data_nasc				DATE,
	mensalidade				NUMBER(10,2)
);


CREATE TABLE ponto (
	id 					SERIAL PRIMARY KEY,
	latitude			NUMBER(12,8),
	longitude			NUMBER(12,8)
);

CREATE TABLE estado (
	id 				SERIAL PRIMARY KEY,
	nome			VARCHAR(255)
);

CREATE TABLE municipio (
	id 				SERIAL PRIMARY KEY,
	nome			VARCHAR(255),
	id_estado		INT REFERENCES estado(id)
);


CREATE TABLE endereco (
	id 					SERIAL PRIMARY KEY,
	logradouro			VARCHAR,
	numero 				VARCHAR,
	complemento			VARCHAR,
	bairro 				VARCHAR,
	cep 				VARCHAR,
	id_ponto			INT REFERENCES ponto(id),
	id_municipio		INT REFERENCES municipio(id)
);

CREATE TABLE transportadora (
	id 					INT PRIMARY KEY REFERENCES pessoa(id),
	id_endereco			INT REFERENCES endereco(id)
);


CREATE TABLE veiculo (
	id 					SERIAL PRIMARY KEY,
	id_transportadora	INT REFERENCES transportadora(id),
	renavam				VARCHAR(11),
	placa				VARCHAR(8),
	capacidade			NUMBER(2)
);


CREATE TABLE rota (
	id 					SERIAL PRIMARY KEY,
	id_veiculo			INT REFERENCES veiculo(id),
	candidata			BOOLEAN
);

CREATE TABLE trajeto (
	id 					SERIAL PRIMARY KEY,
	id_passageiro		INT REFERENCES passageiro(id),
	id_endereco_partida	INT REFERENCES endereco(id),
	id_endereco_chegada	INT REFERENCES endereco(id),
	hora_partida		TIME NOT NULL,
	hora_chegada		TIME NOT NULL,
);

# --- !Downs
 
DROP TABLE IF EXISTS trajeto;
DROP TABLE IF EXISTS rota;
DROP TABLE IF EXISTS veiculo;
DROP TABLE IF EXISTS transportadora;
DROP TABLE IF EXISTS endereco;
DROP TABLE IF EXISTS municipio;
DROP TABLE IF EXISTS estado;
DROP TABLE IF EXISTS ponto;
DROP TABLE IF EXISTS passageiro;
DROP TABLE IF EXISTS pessoa;