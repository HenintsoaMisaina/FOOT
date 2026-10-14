CREATE SEQUENCE SEQ_EQUIPE  START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_JOUEUR  START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_TIR     START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE TABLE EQUIPE (
    id   NUMBER PRIMARY KEY,
    nom  VARCHAR2(50) NOT NULL
);

CREATE TABLE JOUEUR (
    id NUMBER PRIMARY KEY,
    nom VARCHAR2(50) NOT NULL,
    poste VARCHAR2(20) NOT NULL
                CHECK (poste IN ('GARDIEN', 'DEFENSEUR', 'MILIEU', 'ATTAQUANT')),
    id_equipe NUMBER NOT NULL REFERENCES EQUIPE(id),
    x_initial NUMBER(5) NOT NULL,
    y_initial NUMBER(5) NOT NULL
);

CREATE TABLE TIR (
    id NUMBER PRIMARY KEY,
    id_joueur NUMBER NOT NULL REFERENCES JOUEUR(id),
    nom_joueur VARCHAR2(50) NOT NULL,
    minute_jeu NUMBER(3) NOT NULL,
    x_depart NUMBER(5) NOT NULL,
    y_depart NUMBER(5) NOT NULL,
    x_arrivee NUMBER(5) NOT NULL,
    y_arrivee NUMBER(5) NOT NULL,
    but NUMBER(1) NOT NULL CHECK (but IN (0, 1)),
    in_surface NUMBER(1) NOT NULL CHECK (in_surface IN (0, 1))
);

CREATE TABLE TACLE (nom_joueur VARCHAR2(50) NOT NULL, minute_jeu NUMBER(3) NOT NULL);

INSERT INTO EQUIPE (id, nom) VALUES (SEQ_EQUIPE.NEXTVAL, 'FC Barcelona');
INSERT INTO EQUIPE (id, nom) VALUES (SEQ_EQUIPE.NEXTVAL, 'Real Madrid');

INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Ter Stegen',   'GARDIEN',   150, 500, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Kounde',       'DEFENSEUR', 300, 200, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Araujo',       'DEFENSEUR', 300, 400, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Cubarsi',      'DEFENSEUR', 300, 600, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Balde',        'DEFENSEUR', 300, 800, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Pedri',        'MILIEU',    550, 300, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'De Jong',      'MILIEU',    550, 500, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Gavi',         'MILIEU',    550, 700, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Raphinha',     'ATTAQUANT', 750, 300, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Lewandowski',  'ATTAQUANT', 750, 500, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Lamine Yamal', 'ATTAQUANT', 750, 700, (SELECT id FROM EQUIPE WHERE nom = 'FC Barcelona'));

INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Courtois',     'GARDIEN',   1450, 500, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Carvajal',     'DEFENSEUR', 1300, 200, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Rudiger',      'DEFENSEUR', 1300, 400, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Militao',      'DEFENSEUR', 1300, 600, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Mendy',        'DEFENSEUR', 1300, 800, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Valverde',     'MILIEU',    1050, 300, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Bellingham',   'MILIEU',    1050, 500, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Tchouameni',   'MILIEU',    1050, 700, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Rodrygo',      'ATTAQUANT', 850, 300, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Mbappe',       'ATTAQUANT', 850, 500, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));
INSERT INTO JOUEUR (id, nom, poste, x_initial, y_initial, id_equipe) VALUES (SEQ_JOUEUR.NEXTVAL, 'Vinicius Jr',  'ATTAQUANT', 850, 700, (SELECT id FROM EQUIPE WHERE nom = 'Real Madrid'));

COMMIT;


SELECT nom_joueur, COUNT(*) AS buts
FROM TIR
WHERE but = 1
GROUP BY nom_joueur
ORDER BY buts DESC;

ALTER TABLE tir ADD in_surface NUMBER(1) CHECK (in_surface IN (0, 1));  

javac -cp ".:lib/ojdbc11.jar" affichage/*.java bdd/*.java connexion/*.java engine/*.java game/*.java listener/*.java main/*.java player/*.java

java -cp ".:lib/ojdbc11.jar" main.Main

rm affichage/*.class bdd/*.class connexion/*.class engine/*.class game/*.class listener/*.class main/*.class player/*.class