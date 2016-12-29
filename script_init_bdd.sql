DROP TABLE SUB_THEME_QCM;
DROP TABLE QUESTION;
DROP TABLE QCM;
DROP TABLE SUPPLIER;
DROP TABLE ADMIN;
DROP TABLE AUTHORITIES;
DROP TABLE SUB_THEME;
DROP TABLE PLAYER_THEME;
DROP TABLE PLAYER;
DROP TABLE THEME;

drop sequence admin_id_player_seq;
drop sequence authorities_id_player_seq;
drop sequence player_id_seq;
drop sequence player_theme_id_theme_seq;
drop sequence sub_theme_id_seq;
drop sequence sub_theme_id_theme_seq;
drop sequence supplier_id_player_seq;
drop sequence theme_id_seq;


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


INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (101,'Julien', 'Flamen', 'flamen.julien@ragmail.com', '1994-01-23', TRUE, 'Jilief','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player'); 
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (102,'Thomas', 'Ostrowski', 'ostro.thomas@gmail.pl', '1994-01-23', TRUE, 'Ostroluge','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (103,'Floriane', 'Goubel', 'goubel.floriane@fastandfurious.com', '1994-01-01', TRUE, 'Goubelf','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (104,'Vincent', 'Margerin', 'margerin.vincent@papamail.com', '1934-01-01', TRUE, 'ElPadre','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (105,'Jeremy', 'Thach', 'thach.jeremy@dmail.ch', '1984-01-01', TRUE, 'Ching chong','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');


INSERT INTO supplier (id_player, is_valid, is_private_individual) values (104, TRUE, TRUE);
INSERT INTO supplier (id_player, is_valid, is_private_individual, company_name, company_address, siret_number) values (105, FALSE, FALSE, 'Au pavillon des délices', '12 rue Victor Yugo, 75005 Chinatown','12345678912355');

INSERT INTO AUTHORITIES (id_authorities,username,role) values (101,'Jilief','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (102,'Ostroluge','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (103,'Goubelf', 'ROLE_PLAYER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (104,'ElPadre','ROLE_SUPPLIER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (105,'Ching chong','ROLE_SUPPLIER');

INSERT INTO THEME (id, label, description, is_valid) values (101, 'Sport', 'Theme sport', TRUE);
INSERT INTO THEME (id, label, description, is_valid) values (102, 'Art', 'Theme art', FALSE);
INSERT INTO THEME (id, label, description, is_valid) values (103, 'Musique', 'Theme musique', TRUE);

INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (101,101,'Football', 'Le ballon rond. What else ?', TRUE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (102,101,'Rugby', 'Un arrière goût de Ballabriga ici', FALSE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (103,102,'Peinture', 'Monet, monet, monet !', FALSE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (104,102,'Cinéma', 'Ici on trouvera forcément des quizz sur Scarlett !', FALSE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (105,103,'Rap', 'Mes baskets sentent la schnek, trop de putes à mes pieds', TRUE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (106,103,'Jazz', 'De nombreux quizz issus du célèbre site Youjazz', FALSE);

INSERT INTO PLAYER_THEME (id_player, id_theme) values (101,101);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (102,102);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (103,103);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (104,102);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (105,101);

INSERT INTO QCM (id, id_theme, id_supplier, name, description, is_validated, is_free, category, price, minimal_score, is_complete) values (101, 101, 104, 'Ligue 1',
'Un questionnaire pour tous les fans de l élite du football français', TRUE, TRUE, 'sommatif',0,0, TRUE);
INSERT INTO QCM (id, id_theme, id_supplier, name, description, is_validated, is_free, category, price, minimal_score, is_complete) values (102, 102, 105, 'Max et Léon',
'Un questionnaire pour tous les fans du Palma Show', TRUE, TRUE, 'sommatif',0,0, TRUE);
INSERT INTO QCM (id, id_theme, id_supplier, name, description, is_validated, is_free, category, price, minimal_score, is_complete) values (103, 103, 104, 'B2oba',
'Un questionnaire pour tous les fans de rap hardcore', TRUE, TRUE, 'sommatif',0,0, TRUE);

INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (101, 101,
 'Quelle équipe a remporté le championnat de France de L1 lors de la saison 1997-1998 ?', 'RC Lens', 'Olympique Lyonnais', 
 'Olympique de Marseille', 'AS Monaco', 1);
INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (102, 101,
 'Quelle équipe a remporté le championnat de France de L1 lors de la saison 2015-2016 ?', 'Paris Saint Germain',
  'Olympique Lyonnais', 'Olympique de Marseille', 'AS Monaco', 2);
INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (103, 102,
 'Pendant quelle guerre l action se déroule t elle ?', 'La seconde guerre mondiale',
  'La première guerre mondiale', 'La guerre du Vietnam', 'La guerre froide', 1);
INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (104, 102,
 'De quelle ville les 2 personnages sont ils originaires ?', 'Macon',
  'Paris', 'Marseille', 'Lyon', 2);
INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (105, 103,
 'Quel département est cité lors de son célèbre slogan ?', '92', '93', '94', '62', 1);
INSERT INTO QUESTION (id, id_qcm, title, answer, proposition1, proposition2, proposition3, question_number) values (106, 103,
 'En quelle année le rappeur est-il né ?', '1976', '1986', '1982', '1978', 2);

INSERT INTO SUB_THEME_QCM (id_sub_theme, id_qcm) values (101, 101);
INSERT INTO SUB_THEME_QCM (id_sub_theme, id_qcm) values (102, 101);
INSERT INTO SUB_THEME_QCM (id_sub_theme, id_qcm) values (104, 102);
INSERT INTO SUB_THEME_QCM (id_sub_theme, id_qcm) values (105, 103);
