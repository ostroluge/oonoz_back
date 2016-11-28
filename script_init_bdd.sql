DROP TABLE SUPPLIER;
DROP TABLE ADMIN;
DROP TABLE AUTHORITIES;
DROP TABLE SUB_THEME;
DROP TABLE PLAYER_THEME;
DROP TABLE PLAYER;
DROP TABLE THEME;

CREATE TABLE PLAYER (
id BIGSERIAL PRIMARY KEY,
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


INSERT INTO player (firstname, lastname, mail, birthdate, is_active, username, password,is_supplier) values ('Julien', 'Flamen', 'flamen.julien@ragmail.com', '23/01/1994', TRUE, 'Jilief','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE); 
INSERT INTO player (firstname, lastname, mail, birthdate, is_active, username,password,is_supplier) values ('Thomas', 'Ostrowski', 'ostro.thomas@gmail.pl', '23/01/1994', TRUE, 'Ostroluge','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE);
INSERT INTO player (firstname, lastname, mail, birthdate, is_active, username, password,is_supplier) values ('Floriane', 'Goubel', 'goubel.floriane@fastandfurious.com', '01/01/1994', TRUE, 'Goubelf','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE);
INSERT INTO player (firstname, lastname, mail, birthdate, is_active, username, password,is_supplier) values ('Vincent', 'Margerin', 'margerin.vincent@papamail.com', '01/01/1934', TRUE, 'ElPadre','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE);
INSERT INTO player (firstname, lastname, mail, birthdate, is_active, username, password,is_supplier) values ('Jeremy', 'Thach', 'thach.jeremy@dmail.ch', '01/01/1984', TRUE, 'Ching chong','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE);

INSERT INTO admin values (1);
INSERT INTO admin values (2);

INSERT INTO supplier (id_player, is_valid, is_private_individual) values (104, TRUE, TRUE);
INSERT INTO supplier (id_player, is_valid, is_private_individual, company_name, company_address, siret_number) values (105, TRUE, FALSE, 'Au pavillon des délices', '12 rue Victor Yugo, 75005 Chinatown','12345678912355');

INSERT INTO AUTHORITIES (id_authorities,username,role) values (101,'Jilief','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (102,'Ostroluge','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (103,'Goubelf', 'ROLE_PLAYER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (104,'ElPadre','ROLE_SUPPLIER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (105,'Ching chong','ROLE_SUPPLIER');

INSERT INTO THEME (id, label, description, is_valid) values (1, 'Sport', 'Theme sport', TRUE);
INSERT INTO THEME (id, label, description, is_valid) values (2, 'Art', 'Theme art', FALSE);
INSERT INTO THEME (id, label, description, is_valid) values (3, 'Musique', 'Theme musique', TRUE);

INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (1,1,'Football', 'Le ballon rond. What else ?', TRUE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (2,1,'Rugby', 'Un arrière goût de Ballabriga ici', FALSE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (3,2,'Peinture', 'Monet, monet, monet !', TRUE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (4,2,'Cinéma', 'Ici on trouvera forcément des quizz sur Scarlett !', FALSE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (5,3,'Rap', 'Mes baskets sentent la schnek, trop de putes à mes pieds', TRUE);
INSERT INTO SUB_THEME(id, id_theme, label, description, is_valid) values (6,3,'Jazz', 'De nombreux quizz issus du célèbre site Youjazz', FALSE);

INSERT INTO PLAYER_THEME (id_player, id_theme) values (1,1);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (2,2);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (3,3);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (4,2);
INSERT INTO PLAYER_THEME (id_player, id_theme) values (5,1);