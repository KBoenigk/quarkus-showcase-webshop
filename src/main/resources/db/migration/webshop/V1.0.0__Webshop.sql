DROP TABLE IF EXISTS public.PRODUKT;
CREATE TABLE public.PRODUKT (
                                 ID BIGINT NOT NULL PRIMARY KEY,
                                 NAME VARCHAR(64) NOT NULL,
                                 PREIS DECIMAL NOT NULL,
                                 BESCHREIBUNG VARCHAR(64)
);

INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (1, 'Marco', 0, 'Polo');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (2, 'Ping', 0,'Pong');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (3, 'Moin', 0,'Moin');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (4, 'Hi', 0,'Hey');