USE cbpapiers;
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('AL30', 'FILM','ALUMINIUM ROULEAU 30CMX150M + BTE DISTRIBUTRICE', 11.21);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('AM195', 'PLATEAU','ASSIETTE METALISEE OR 195 (50)', 7.12);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('VBP138', 'BOITE','BOITE PATIS FOND UNI - VERNI IMP 13X13X8', 0.19);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('VSA138', 'BOITE','BOITE PATIS FOND VIF VERNI IMP 13X13X8', 0.18);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('BPZ244', 'BOITE','BOITE PIZZA GENERIQUES 24X24X4 (100)', 16.35);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('DP113', 'DECORATION','LAPIN COQUIN REF 2368 (48)', 21.3);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('SCF2', 'ZAC','SAC CELLO FOND CARTON NEUTRE 120X275 (100)', 11.89);
INSERT INTO F_ARTICLE (AR_Ref,FA_CodeFamille,AR_Design,AR_Prixven) VALUES ('GT35', 'GOBELETVER','GOBELET PP TRANSPARENT 35 CL JAUGE A 30 ET 25 (20*50)', 63.4);


INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('ALUMINIUM ROULEAU 30CMX150M + BTE DISTRIBUTRICE');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('ASSIETTE METALISEE OR 195 (50)');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('BOITE PATIS FOND UNI - VERNI IMP 13X13X8');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('BOITE PATIS FOND VIF VERNI IMP 13X13X8');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('BOITE PIZZA GENERIQUES 24X24X4 (100)');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('LAPIN COQUIN REF 2368 (48)');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('SAC CELLO FOND CARTON NEUTRE 120X275 (100)');
INSERT INTO ARTICLE_DETAILS (AR_Details) VALUES ('GOBELET PP TRANSPARENT 35 CL JAUGE A 30 ET 25 (20*50)');


INSERT INTO CITY (CT_CodePostal,CT_Ville) VALUES ('F-54310','HOMECOURT');
INSERT INTO CITY (CT_CodePostal,CT_Ville) VALUES ('57000','METZ');
INSERT INTO CITY (CT_CodePostal,CT_Ville) VALUES ('F-57890','PORCELETTE');
INSERT INTO CITY (CT_CodePostal,CT_Ville) VALUES ('F-54300','LUNEVILLE');



INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA','109 AVENUE DE LA REPUBLIQUE', 'SORA PIZZA','03 82 33 84 68', 1,'sorrapizza@email.com', 'secret',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city) VALUES
('BOULANGERIELOSSO','24 RUE DE DIESEN', 'BOULANGERIE LOSSON RG','03 87 93 06 30', 3, 'sorrapizza24@email.com', 'secret',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA2','109 AVENUE DE LA REPUBLIQUE2', 'SORA PIZZA 2','03 82 33 84 68', 1,'sorrapizza12@email.com', 'secret',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA3','109 AVENUE DE LA REPUBLIQUE3', 'SORA PIZZA 3','03 82 33 84 68', 1, 'sorrapizza1@email.com','secret1',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA4','109 AVENUE DE LA REPUBLIQUE4', 'SORA PIZZA 4','03 82 33 84 68', 1, 'sorrapizza2@email.com','secret2',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA5','109 AVENUE DE LA REPUBLIQUE5', 'SORA PIZZA 5','03 82 33 84 68', 1, 'sorrapizza3@email.com','secret3',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA6','109 AVENUE DE LA REPUBLIQUE6', 'SORA PIZZA 6','03 82 33 84 68', 1, 'sorrapizza4@email.com','secret4',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA7','109 AVENUE DE LA REPUBLIQUE7', 'SORA PIZZA 7','03 82 33 84 68', 1, 'sorrapizza5@email.com','secret5',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA8','109 AVENUE DE LA REPUBLIQUE8', 'SORA PIZZA 8','03 82 33 84 68', 1,'sorrapizza6@email.com', 'secret6',1, 0);
INSERT INTO F_COMPTET(CT_NUM,CT_Adresse,CT_Intitule,CT_Telephone,id_city, CT_EMail, password, isActive, isAdmin) VALUES ('SORAPIZZA9','109 AVENUE DE LA REPUBLIQUE9', 'SORA PIZZA 1','03 82 33 84 68', 1,'sorrapizza7@email.com', 'secret7',1, 0);


INSERT INTO CLIENT_ORDER(DO_PIECE,CT_NUM,dateCommande) VALUES ('COMMANDE1','SORAPIZZA','2020-07-16 14:00:01');
INSERT INTO CLIENT_ORDER(DO_PIECE,CT_NUM,dateCommande) VALUES ('COMMANDE2','SORAPIZZA', '2020-07-11 14:00:01');
INSERT INTO CLIENT_ORDER(DO_PIECE,CT_NUM,dateCommande) VALUES ('COMMANDE3','SORAPIZZA', '2020-07-02 14:00:01');
INSERT INTO CLIENT_ORDER(DO_PIECE,CT_NUM,dateCommande) VALUES ('COMMANDE4','SORAPIZZA2', '2020-07-01 14:00:01');

INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (10,'AL30','COMMANDE1');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (5,'AM195','COMMANDE1');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (4,'VBP138','COMMANDE1');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (9,'SCF2','COMMANDE1');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (3,'GT35','COMMANDE2');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (4,'DP113','COMMANDE3');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (12,'BPZ244','COMMANDE4');
INSERT INTO F_DOCLIGNE(quantity,AR_Ref,DO_PIECE) VALUES (20,'BPZ244','COMMANDE3');

INSERT INTO F_ARTCLIENT (AR_Ref,CT_NUM,AR_remise,AC_PrixVen) VALUES ('AL30','SORAPIZZA',10, 0);


INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('AL30','SORAPIZZA',1);
INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('AM195','SORAPIZZA',2);
INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('VBP138','SORAPIZZA',3);
INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('BPZ244','SORAPIZZA',4);
INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('GT35','SORAPIZZA',5);

INSERT INTO TOP_ARTICLE(AR_Ref,CT_NUM,position)  VALUES ('AL30','SORAPIZZA2',1);