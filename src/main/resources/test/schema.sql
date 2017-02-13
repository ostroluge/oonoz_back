

CREATE TABLE PLAYER (
id BIGSERIAL PRIMARY KEY,
firstname varchar(20) NOT NULL,
lastname varchar(20) NOT NULL,
mail varchar(50) NOT NULL unique,
password varchar(100) NOT NULL,
username varchar(20) NOT NULL unique,
birthdate date NOT NULL,
is_active boolean NOT NULL,
is_supplier boolean NOT NULL,
type_user varchar(20)NOT NULL
);

CREATE TABLE SUPPLIER (
id_player BIGSERIAL references player(id) ON DELETE CASCADE,
is_valid boolean not null,
is_private_individual boolean not null,
company_name varchar(40),
company_address varchar(150),
siret_number varchar(14) unique
);

CREATE TABLE ADMIN (
id_player BIGSERIAL references player(id) ON DELETE CASCADE
);

CREATE TABLE AUTHORITIES(
id_authorities BIGSERIAL PRIMARY KEY,
username varchar(20) not null,
role VARCHAR(20) not null,
CONSTRAINT authorities_player_fkey FOREIGN KEY (id_authorities)
      REFERENCES player (id)
 
);

CREATE TABLE THEME (
id SERIAL PRIMARY KEY,
label VARCHAR(20) NOT NULL UNIQUE,
description VARCHAR(100) NOT NULL,
icon TEXT,
is_valid boolean NOT NULL
);

CREATE TABLE SUB_THEME (
id SERIAL PRIMARY KEY,
id_theme SERIAL references theme(id) ON DELETE CASCADE,
label VARCHAR(20) NOT NULL,
description VARCHAR(100) NOT NULL,
icon TEXT,
is_valid boolean NOT NULL
);

CREATE TABLE PLAYER_THEME (
id_player BIGSERIAL references player(id) ON DELETE CASCADE,
id_theme SERIAL references theme(id) ON DELETE CASCADE
);


CREATE TABLE QCM (
id BIGSERIAL PRIMARY KEY,
id_theme SERIAL references theme(id) ON DELETE CASCADE,
id_supplier BIGSERIAL references player(id) ON DELETE CASCADE,
name VARCHAR(40) not null,
description VARCHAR(150) not null,
is_validated boolean not null,
is_free boolean not null,
price float(2),
icon TEXT,
prize_name VARCHAR(50),
prize_description VARCHAR(150),
is_complete boolean not null,
minimal_score integer,
category VARCHAR(10) not null
);

CREATE TABLE QUESTION (
id BIGSERIAL PRIMARY KEY, 
id_qcm BIGSERIAL references QCM(id) ON DELETE CASCADE,
question_number integer not null,
title VARCHAR(200) not null,
media_type VARCHAR(10),
media TEXT,
answer VARCHAR(50) not null,
proposition1 VARCHAR(50) not null,
proposition2 VARCHAR(50) not null,
proposition3 VARCHAR(50) not null,
time integer
);

CREATE TABLE SUB_THEME_QCM (
id_sub_theme SERIAL references sub_theme(id) ON DELETE CASCADE,
id_qcm BIGSERIAL references qcm(id) ON DELETE CASCADE
);

