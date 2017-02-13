INSERT INTO player (id,firstname, lastname, mail, birthdate, is_active, username, password,is_supplier,type_user) values (101,'Julien', 'Flamen', 'flamen.julien@ragmail.com', '1994-01-23', TRUE, 'Jilief','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player'); 
INSERT INTO player (id,firstname, lastname, mail, birthdate, is_active, username,password,is_supplier,type_user) values (102,'Thomas', 'Ostrowski', 'ostro.thomas@gmail.pl', '1994-01-23', TRUE, 'Ostroluge','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id,firstname, lastname, mail, birthdate, is_active, username, password,is_supplier,type_user) values (103,'Floriane', 'Goubel', 'goubel.floriane@fastandfurious.com', '1994-01-23', TRUE, 'Goubelf','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id,firstname, lastname, mail, birthdate, is_active, username, password,is_supplier,type_user) values (104,'Vincent', 'Margerin', 'margerin.vincent@papamail.com', '1994-01-23', TRUE, 'ElPadre','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');
INSERT INTO player (id,firstname, lastname, mail, birthdate, is_active, username, password,is_supplier,type_user) values (105,'Jeremy', 'Thach', 'thach.jeremy@dmail.ch', '1994-01-23', TRUE, 'Ching chong','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');



INSERT INTO supplier (id_player, is_valid, is_private_individual) values (104, TRUE, TRUE);
INSERT INTO supplier (id_player, is_valid, is_private_individual, company_name, company_address, siret_number) values (105, TRUE, FALSE, 'Au pavillon des délices', '12 rue Victor Yugo, 75005 Chinatown','12345678912355');

INSERT INTO AUTHORITIES (id_authorities,username,role) values (101,'Jilief','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (102,'Ostroluge','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (103,'Goubelf', 'ROLE_PLAYER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (104,'ElPadre','ROLE_SUPPLIER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (105,'Ching chong','ROLE_SUPPLIER');

