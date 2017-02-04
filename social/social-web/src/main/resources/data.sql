-- Times --
INSERT INTO TIME (ID_TIME, NM_TIME, ESCUDO_TIME) 
VALUES(1, 'Internacional', 'https://static.wixstatic.com/media/29c778_9dca55e51ad34b6fb34bb2e676c61e81.png/v1/fill/w_435,h_435,al_c,usm_0.66_1.00_0.01/29c778_9dca55e51ad34b6fb34bb2e676c61e81.png');

INSERT INTO TIME (ID_TIME, NM_TIME, ESCUDO_TIME) 
VALUES(2, 'Gremio', 'https://static.wixstatic.com/media/29c778_95c9953ebf464de2af02ba37f3bd5a2b.png/v1/fill/w_435,h_435,al_c,usm_0.66_1.00_0.01/29c778_95c9953ebf464de2af02ba37f3bd5a2b.png');

INSERT INTO TIME (ID_TIME, NM_TIME, ESCUDO_TIME) 
VALUES(3, 'Brasil de Pelotas', 'https://static.wixstatic.com/media/29c778_f85b694a82c245a1a74eccd41b11d6b9.png/v1/fill/w_435,h_435,al_c,usm_0.66_1.00_0.01/29c778_f85b694a82c245a1a74eccd41b11d6b9.png');

INSERT INTO TIME (ID_TIME, NM_TIME, ESCUDO_TIME) 
VALUES(4, 'Juventude', 'https://static.wixstatic.com/media/29c778_a8341405641f4549886ede60cf29ce6c.png/v1/fill/w_435,h_435,al_c,usm_0.66_1.00_0.01/29c778_a8341405641f4549886ede60cf29ce6c.png');

INSERT INTO TIME (ID_TIME, NM_TIME, ESCUDO_TIME) 
VALUES(5, 'Pelotas', 'https://static.wixstatic.com/media/29c778_84787c720f644d77ba2a029cd2ee7d54.png/v1/fill/w_386,h_386,al_c,usm_0.66_1.00_0.01/29c778_84787c720f644d77ba2a029cd2ee7d54.png');

-- Perfis --
INSERT INTO PERFIL(ID_PERFIL, NOME, EMAIL, DATA_NASCIMENTO, SEXO, ID_TIME,IMAGEM_PERFIL, CAPA_PERFIL)
VALUES(1000, 'Arthur Lima de Souza', 'arthurlds73@gmail.com','27/11/98 14:55:00','MASCULINO', 1, '/imgs/perfil/arthur.jpg', '/imgs/capa/capaArthur.jpg');

INSERT INTO PERFIL(ID_PERFIL, NOME, EMAIL, DATA_NASCIMENTO, SEXO, ID_TIME,IMAGEM_PERFIL, CAPA_PERFIL)
VALUES(1001, 'Rafael de Souza', 'rafaeldesouza@gmail.com','06/04/88 14:55:00','MASCULINO', 1, '/imgs/perfil/chaves.jpg', '/imgs/capa/fernandao_taca.jpg');

INSERT INTO PERFIL(ID_PERFIL, NOME, EMAIL, DATA_NASCIMENTO, SEXO, ID_TIME,IMAGEM_PERFIL, CAPA_PERFIL)
VALUES(1002, 'Mara Regina Lima de Souza', 'maralima@gmail.com','17/01/54 14:55:00','FEMININO', 3, '/imgs/perfil/donaflorinda.png', '/imgs/capa/fernandao_taca.jpg');

INSERT INTO PERFIL(ID_PERFIL, NOME, EMAIL, DATA_NASCIMENTO, SEXO, ID_TIME,IMAGEM_PERFIL, CAPA_PERFIL)
VALUES(1003, 'Larri Angeli de Souza', 'larridesouza@gmail.com','06/06/54 14:55:00','MASCULINO', 1, '/imgs/perfil/seumadruga.jpg', '/imgs/capa/capaArthur.jpg');

INSERT INTO PERFIL(ID_PERFIL, NOME, EMAIL, DATA_NASCIMENTO, SEXO, ID_TIME,IMAGEM_PERFIL, CAPA_PERFIL)
VALUES(1004, 'Sandro Sotilli', 'sandrosotilli73@gmail.com','18/06/73 14:55:00','MASCULINO', 5, '/imgs/perfil/sotiliFoto.jpg', '/imgs/capa/sotiliCapa.jpg');

-- Usuários --
INSERT INTO USUARIO (ID_USUARIO, EMAIL, SENHA, ID_PERFIL) 
VALUES(1000, 'arthurlds73@gmail.com', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1000);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, SENHA, ID_PERFIL) 
VALUES(1001, 'rafaeldesouza@gmail.com', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1001);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, SENHA, ID_PERFIL) 
VALUES(1002, 'maralima@gmail.com', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1002);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, SENHA, ID_PERFIL) 
VALUES(1003, 'larridesouza@gmail.com', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1003);

INSERT INTO USUARIO (ID_USUARIO, EMAIL, SENHA, ID_PERFIL) 
VALUES(1004, 'sandrosotilli73@gmail.com', '$2a$10$u1A9pPCNLpUiNwvREkHDOOivSthu28yRmNDddtOtykm6lBFVDD1S2', 1004);







