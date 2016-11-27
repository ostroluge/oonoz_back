/*DROP TABLE SUPPLIER;
DROP TABLE ADMIN;
DROP TABLE AUTHORITIES;
DROP TABLE SUB_THEME;
DROP TABLE PLAYER_THEME;
DROP TABLE PLAYER;
DROP TABLE THEME;*/

CREATE TABLE PLAYER (
id integer PRIMARY KEY,
firstname varchar(20) NOT NULL,
lastname varchar(20) NOT NULL,
mail varchar(50) NOT NULL unique,
password varchar(100) NOT NULL,
username varchar(20) NOT NULL unique,
birthdate date NOT NULL,
is_active boolean NOT NULL,
is_supplier boolean NOT NULL
);

CREATE TABLE SUPPLIER (
id_player integer references player(id) ON DELETE CASCADE,
is_valid boolean not null,
is_private_individual boolean not null,
company_name varchar(40),
company_address varchar(150),
siret_number varchar(14) unique
);



CREATE TABLE AUTHORITIES(
id_authorities integer PRIMARY KEY,
username varchar(20) not null,
role VARCHAR(20) not null,
CONSTRAINT authorities_player_fkey FOREIGN KEY (id_authorities)
      REFERENCES player (id)
 
);

