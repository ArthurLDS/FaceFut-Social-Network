-- Times --
INSERT INTO TIME (ID_TIME, NM_TIME) VALUES(1, 'Internacional');
INSERT INTO TIME (ID_TIME, NM_TIME) VALUES(2, 'Gremio');
INSERT INTO TIME (ID_TIME, NM_TIME) VALUES(3, 'Brasil de Pelotas');
INSERT INTO TIME (ID_TIME, NM_TIME) VALUES(4, 'Juventude');

-- Usuários --
INSERT INTO USUARIO (ID_USUARIO, EMAIL, NM_USUARIO, SENHA, TIME_ID_TIME) 
VALUES(5, 'arthurlds73@gmail.com', 'Arthur Lima de Souza', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, NM_USUARIO, SENHA, TIME_ID_TIME) 
VALUES(6, 'rafaeldesouza@gmail.com', 'Rafael de Souza', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, NM_USUARIO, SENHA, TIME_ID_TIME) 
VALUES(7, 'maralima@gmail.com', 'Mara Regina Lima de Souza', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, NM_USUARIO, SENHA, TIME_ID_TIME) 
VALUES(8, 'larridesouza@gmail.com', 'Larri Angeli de Souza', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1);

-- Amigos -- 
INSERT INTO AMIGO (ID_AMIGO, NM_AMIGO, EMAIL, TIME_ID_TIME) 
VALUES(5, 'Arthur Lima de Souza', 'arthurlds73@gmail.com', 1);

INSERT INTO AMIGO (ID_AMIGO, NM_AMIGO, EMAIL, TIME_ID_TIME) 
VALUES(6, 'Rafael de Souza', 'rafaeldesouza@gmail.com', 1);

INSERT INTO AMIGO (ID_AMIGO, NM_AMIGO, EMAIL, TIME_ID_TIME) 
VALUES(7, 'Mara Regina Lima de Souza', 'maralima@gmail.com', 1);

INSERT INTO AMIGO (ID_AMIGO, NM_AMIGO, EMAIL, TIME_ID_TIME) 
VALUES(8, 'Larri Angeli de Souza', 'larridesouza@gmail.com', 1);







