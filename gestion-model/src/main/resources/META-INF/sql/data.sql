INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Robert','robert@example.com','sha1:64000:18:Bzm4Zh1Ayoy61FY0kc1dzxrTKpSqUev9:MwhHd3wfpc9H+LJqWBj3lXGD','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Admin','admin@example.com','sha1:64000:18:n7P/Rpanhn48G7K3JW7Sy7xBnTF4XeiR:eZy3uvxONUuwpQgD7F5kpiYE','Administrateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Jack','jack@example.com','sha1:64000:18:/o/5C8J/QZdE68xOi5P5XUp+N+1HVLz9:jsULk9DH0q73bEl0LGfdLQRE','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Pay','paymentUser@dukesforest.com','sha1:64000:18:qlmDg8C4+X7BBGg0QIRo3wojbrndS+W8:iYm4Qil6xlHetLr8oDZdkFD0','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Robert','robert@exampl.com','sha1:64000:18:ICJ4FayH4OF8Z3tP4bODu9yEYeBQCs3Q:aYNM6Qn25ajJ82bU56XEOAqI','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Admin','admin@exampl.com','sha1:64000:18:vmLhsijjCCHf5LMnY2xU+hX89SAVQa5t:/eqLMsCzP82JREWNJbpQWKpI','Administrateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Jack','jack@exampl.com','sha1:64000:18:j1FV0cNg4yQd3/3l54C2j3sU5v9cNdBq:LXF0+rVbJlI2YZ/NygTlQOYt','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Payment','paymentUser@dukesfores.com','sha1:64000:18:PpXe/BDWk0Pgqa0/rx7IN5F135BnFzTQ:HRjEZTWP39hVJoBxww6oDQfw','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Robert','robert@examp.com','sha1:64000:18:DyId44zZz6HOQp1UFcXwWCGcwb4oRd0v:rL2wsRse3TodYyqc36Tj80UD','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Admin','admin@examp.com','sha1:64000:18:RMwp/vgXzdvCmB4AiH9OURVlRqqiD1D3:GirfmfVZM72zZrDrhTBADoGS','Administrateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Jack','jack@examp.com','sha1:64000:18:lmbR9mGAnY53z1sClEhaFDb1kSujUnLZ:rl9Ds9GKg2Gvr6vh6LXSFY2M','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Payment','paymentUser@dukesfore.com','sha1:64000:18:S6GL8m+Yg3N3ZlJWtzHAEXAqqHb/qbws:/x6CYcllcoUdR0sDCeV5seUA','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Robert','robert@xample.com','sha1:64000:18:rg8bB8BKU/wMH1W1KRSvP1pQOnQeSU+6:sCVN/6sQCt1MuBZoVFsbN7ch','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Admin','admin@xample.com','sha1:64000:18:ntmZ3oGpJwX9RFzORaW/zSkOsSGptQ3v:De/T48WkR+IcJWjM1ROCzVk0','Administrateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Jack','jack@xample.com','sha1:64000:18:ukuNJ54u2dRuRuwyRY4cnQB+EGPXDTw0:XIMQylP+EhwTaNdgqlQgQFsU','Utilisateur');
INSERT INTO PROFIL (NOM,EMAIL,MDP,DTYPE) VALUES ('Payment','paymentUser@ukesforest.com','sha1:64000:18:Z3bSTATaaqhq92XfsU1tWOwrY8BqF76O:Qg63bTojXlI3cCzDtpjcQFP6','Utilisateur');

INSERT INTO GROUPES (NOM, DESCRIPTION) VALUES ('USERS', 'Utilisateurs du site.');
INSERT INTO GROUPES (NOM, DESCRIPTION) VALUES ('ADMINS', 'Administrateur du site.');

INSERT INTO PROFIL_GROUPES (NOM,EMAIL) VALUES ('USERS','robert@example.com');
INSERT INTO PROFIL_GROUPES (NOM,EMAIL) VALUES ('ADMINS','admin@example.com');
INSERT INTO PROFIL_GROUPES (NOM,EMAIL) VALUES ('USERS','jack@example.com');
INSERT INTO PROFIL_GROUPES (NOM,EMAIL) VALUES ('USERS','paymentUser@dukesforest.com');

INSERT INTO ENTREPRISE (RAISON_SOCIALE,FORME_JURIDIQUE,NATURE,SIRET,NUMERO_TVA,ENTREPRISE_ID,PROFIL_ID) VALUES ('Bank\'s','SAS','Commerciale','12345678901234',NULL,NULL,3);
INSERT INTO ENTREPRISE (RAISON_SOCIALE,FORME_JURIDIQUE,NATURE,SIRET,NUMERO_TVA,ENTREPRISE_ID,PROFIL_ID) VALUES ('Chez Titi','CAPE','Coubeuse','09876543214321',NULL,1,NULL);
INSERT INTO ENTREPRISE (RAISON_SOCIALE,FORME_JURIDIQUE,NATURE,SIRET,NUMERO_TVA,ENTREPRISE_ID,PROFIL_ID) VALUES ('Au march??','SARL','Artisanale','098 765 432 11234',NULL,1,NULL);

INSERT INTO PERSONNE (CIVILITE,NOM,PRENOM,COORDONNEES_ID,ENTREPRISE_ID) VALUES ('Mlle', 'PHILO', 'Sophie', 2, 1);
INSERT INTO PERSONNE (CIVILITE,NOM,PRENOM,COORDONNEES_ID,ENTREPRISE_ID) VALUES ('Mr.', 'ITE', 'Alex', 4, 1);

INSERT INTO COORDONNEES (RUE,CODE_POSTAL,VILLE,PAYS,EMAIL,TEL,FAX,ENTREPRISE_ID) VALUES ('Avenue dor??e','75 001','Paris',NULL,NULL,NULL,NULL,1);
INSERT INTO COORDONNEES (RUE,CODE_POSTAL,VILLE,PAYS,EMAIL,TEL,FAX,ENTREPRISE_ID) VALUES ('6 rue des m??ditation.','37 520','D??cartes',NULL,NULL,NULL,NULL,NULL);
INSERT INTO COORDONNEES (RUE,CODE_POSTAL,VILLE,PAYS,EMAIL,TEL,FAX,ENTREPRISE_ID) VALUES ('102 ruelle sure.','09 082','Blou?? sur courge.',NULL,NULL,NULL,NULL,2);
INSERT INTO COORDONNEES (RUE,CODE_POSTAL,VILLE,PAYS,EMAIL,TEL,FAX,ENTREPRISE_ID) VALUES ('Cit?? des lilas.','98 000','Pleign??',NULL,NULL,NULL,NULL,NULL);
INSERT INTO COORDONNEES (RUE,CODE_POSTAL,VILLE,PAYS,EMAIL,TEL,FAX,ENTREPRISE_ID) VALUES ('24 rue grande.','36 700','Ch??tillon sur Indre',NULL,NULL,NULL,NULL,3);
