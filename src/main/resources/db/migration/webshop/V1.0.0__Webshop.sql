DROP TABLE IF EXISTS public.PRODUKT;
CREATE TABLE public.PRODUKT (
                                 ID BIGINT NOT NULL PRIMARY KEY,
                                 NAME VARCHAR(64) NOT NULL,
                                 PREIS DECIMAL NOT NULL,
                                 BESCHREIBUNG VARCHAR(64)
);

INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (1, 'Falafel Schmaus', 9.8, 'Ein leckerer Salat.');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (2, 'Rosinenbomber Bowl', 11.4, 'Eine bunte Bowl.');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (3, 'Ciabatta Sticks', 1.0, 'Eine sättigende Beilage.');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (4, 'Bananenbrot', 2.4,'Eine süße Nachspeise.');
INSERT INTO public.PRODUKT (ID, NAME, PREIS, BESCHREIBUNG) VALUES (5, 'Lemonaid Maracuja', 2.5,'Eine erfrischende Limo.');