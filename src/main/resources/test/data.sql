INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (101,'Julien', 'Flamen', 'flamen.julien@ragmail.com', '1994-01-23', TRUE, 'Jilief','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player'); 
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (102,'Thomas', 'Ostrowski', 'ostro.thomas@gmail.pl', '1994-01-23', TRUE, 'Ostroluge','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (103,'Floriane', 'Goubel', 'goubel.floriane@fastandfurious.com', '1994-01-01', TRUE, 'Goubelf','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',FALSE,'Player');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (104,'Vincent', 'Margerin', 'margerin.vincent@papamail.com', '1934-01-01', TRUE, 'ElPadre','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');
INSERT INTO player (id, firstname, lastname, mail, birthdate, is_active, username, password, is_supplier, type_user) values (106,'Jeremy', 'Thach', 'thach.jeremy@dmail.ch', '1984-01-01', TRUE, 'Ching chong','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',TRUE,'Supplier');


INSERT INTO supplier (id_player, is_valid, is_private_individual) values (104, TRUE, TRUE);
INSERT INTO supplier (id_player, is_valid, is_private_individual, company_name, company_address, siret_number) values (106, FALSE, FALSE, 'Au pavillon des délices', '12 rue Victor Yugo, 75005 Chinatown','12345678912355');

INSERT INTO AUTHORITIES (id_authorities,username,role) values (101,'Jilief','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (102,'Ostroluge','ROLE_ADMIN');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (103,'Goubelf', 'ROLE_PLAYER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (104,'ElPadre','ROLE_SUPPLIER');
INSERT INTO AUTHORITIES (id_authorities,username,role) values (106,'Ching chong','ROLE_SUPPLIER');


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
INSERT INTO PLAYER_THEME (id_player, id_theme) values (106,101);

INSERT INTO QCM (id, id_theme, id_supplier, name, description, is_validated, is_free, category, price, minimal_score, is_complete) values (101, 101, 104, 'Ligue 1',
'Un questionnaire pour tous les fans de l élite du football français', TRUE, TRUE, 'sommatif',0,0, TRUE);
INSERT INTO QCM (id, id_theme, id_supplier, name, description, is_validated, is_free, category, price, minimal_score, is_complete) values (102, 102, 106, 'Max et Léon',
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