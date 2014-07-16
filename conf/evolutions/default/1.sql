# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions
 
# --- !Ups
 
CREATE TABLE pessoa (
  id                        SERIAL PRIMARY KEY,
  nome                      VARCHAR(255) NOT NULL
);
 
# --- !Downs
 
DROP TABLE IF EXISTS pessoa;