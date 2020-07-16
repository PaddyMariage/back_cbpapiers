USE [cbpapiers]
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('AL30', 'FILM','ALUMINIUM ROULEAU 30CMX150M + BTE DISTRIBUTRICE', 11.21);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('AM195', 'PLATEAU','ASSIETTE METALISEE OR 195 (50)', 7.12);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('VBP138', 'BOITE','BOITE PATIS FOND UNI - VERNI IMP 13X13X8', 0.19);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('VSA138', 'BOITE','BOITE PATIS FOND VIF VERNI IMP 13X13X8', 0.18);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('BPZ244', 'BOITE','BOITE PIZZA GENERIQUES 24X24X4 (100)', 16.35);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('DP113', 'DECORATION','LAPIN COQUIN REF 2368 (48)', 21.3);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('SCF2', 'ZAC','SAC CELLO FOND CARTON NEUTRE 120X275 (100)', 11.89);
INSERT INTO [dbo].[F_ARTICLE] ([AR_Ref],[FA_CodeFamille],[AR_Design],[AR_Priven]) VALUES ('GT35', 'GOBELETVER','GOBELET PP TRANSPARENT 35 CL JAUGE A 30 ET 25 (20*50)', 63.4);


INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('ALUMINIUM ROULEAU 30CMX150M + BTE DISTRIBUTRICE','AL30');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('ASSIETTE METALISEE OR 195 (50)','AM195');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('BOITE PATIS FOND UNI - VERNI IMP 13X13X8','VBP138');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('BOITE PATIS FOND VIF VERNI IMP 13X13X8','VSA138');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('BOITE PIZZA GENERIQUES 24X24X4 (100)','BPZ244');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('LAPIN COQUIN REF 2368 (48)','DP113');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('SAC CELLO FOND CARTON NEUTRE 120X275 (100)','SCF2');
INSERT INTO [dbo].[ARTICLE_DETAILS] (AR_Details,AR_Ref) VALUES ('GOBELET PP TRANSPARENT 35 CL JAUGE A 30 ET 25 (20*50)','GT35');


INSERT INTO [dbo].[CITY] (id,CT_CodePostal,CT_Ville) VALUES (1,'F-54310','HOMECOURT');
INSERT INTO [dbo].[CITY] (id,CT_CodePostal,CT_Ville) VALUES (2,'57000','METZ');
INSERT INTO [dbo].[CITY] (id,CT_CodePostal,CT_Ville) VALUES (3,'F-57890','PORCELETTE');
INSERT INTO [dbo].[CITY] (id,CT_CodePostal,CT_Ville) VALUES (4,'F-54300','LUNEVILLE');



INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA','109 AVENUE DE LA REPUBLIQUE', 'SORA PIZZA','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES
('BOULANGERIELOSSO','24 RUE DE DIESEN', 'BOULANGERIE LOSSON RG','03 87 93 06 30', 3);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA2','109 AVENUE DE LA REPUBLIQUE2', 'SORA PIZZA 2','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA3','109 AVENUE DE LA REPUBLIQUE3', 'SORA PIZZA 3','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA4','109 AVENUE DE LA REPUBLIQUE4', 'SORA PIZZA 4','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA5','109 AVENUE DE LA REPUBLIQUE5', 'SORA PIZZA 5','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA6','109 AVENUE DE LA REPUBLIQUE6', 'SORA PIZZA 6','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA7','109 AVENUE DE LA REPUBLIQUE7', 'SORA PIZZA 7','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA8','109 AVENUE DE LA REPUBLIQUE8', 'SORA PIZZA 8','03 82 33 84 68', 1);
INSERT INTO [dbo].[F_COMPTET]([CT_NUM],[CT_Adresse],[CT_Intitule],[CT_Telephone],[id_city]) VALUES ('SORAPIZZA9','109 AVENUE DE LA REPUBLIQUE9', 'SORA PIZZA 1','03 82 33 84 68', 1);

INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza@email.com',1,0,'passsorrapizza','SORAPIZZA');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza2@email.com',1,0,'passsorrapizza2','SORAPIZZA2');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza3@email.com',1,0,'passsorrapizza3','SORAPIZZA3');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza4@email.com',1,0,'passsorrapizza4','SORAPIZZA4');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza5@email.com',1,0,'passsorrapizza5','SORAPIZZA5');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza6@email.com',1,0,'passsorrapizza6','SORAPIZZA6');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza7@email.com',1,0,'passsorrapizza7','SORAPIZZA7');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza8@email.com',1,0,'passsorrapizza8','SORAPIZZA8');
INSERT INTO [dbo].[F_COMPTET_INFO]([CT_EMail],[isActive],[isAdmin],[password],[CT_Num]) VALUES ('sorrapizza8@email.com',1,0,'passsorrapizza9','SORAPIZZA9');

INSERT INTO [dbo].[CLIENT_ORDER]([DO_PIECE],[CT_NUM]) VALUES ('COMMANDE1','SORAPIZZA');
INSERT INTO [dbo].[CLIENT_ORDER]([DO_PIECE],[CT_NUM]) VALUES ('COMMANDE2','SORAPIZZA');
INSERT INTO [dbo].[CLIENT_ORDER]([DO_PIECE],[CT_NUM]) VALUES ('COMMANDE3','SORAPIZZA');
INSERT INTO [dbo].[CLIENT_ORDER]([DO_PIECE],[CT_NUM]) VALUES ('COMMANDE4','SORAPIZZA2');

INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (10,'AL30','COMMANDE1');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (5,'AM195','COMMANDE1');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (4,'VBP138','COMMANDE1');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (9,'SCF2','COMMANDE1');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (3,'GT35','COMMANDE2');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (4,'DP113','COMMANDE3');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (12,'BPZ244','COMMANDE4');
INSERT INTO [dbo].[F_DOCLIGNE]([quantity],[AR_Ref],[DO_PIECE]) VALUES (20,'BPZ244','COMMANDE3');

INSERT INTO [dbo].[F_ARTCLIENT] ([AR_Ref],[CT_NUM],[AR_remise]) VALUES ('AL30','SORAPIZZA',10);


INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('AL30','SORAPIZZA',1);
INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('AM195','SORAPIZZA',2);
INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('VBP138','SORAPIZZA',3);
INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('BPZ244','SORAPIZZA',4);
INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('GT35','SORAPIZZA',5);

INSERT INTO [dbo].[TOP_ARTICLE]([AR_Ref] ,[CT_NUM],[position])  VALUES ('AL30','SORAPIZZA2',1);