package de.openknowledge.projects.webshop.domain;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ZahlungsBetragUnitTest {

    @Test
    public void isBetragCorrect() {

        Produkt produkt = Produkt.Builder().setProduktName("Bowl")
                                            .setProduktPreis(BigDecimal.valueOf(10.0))
                                            .build();

        ProduktListenElement element = ProduktListenElement.Builder().setProdukt(produkt)
                                                                        .setProduktMenge(2)
                                                                        .build();

        ProduktListe liste = ProduktListe.Builder().addProdukt(element)
                                                    .build();

        LieferAdresse adresse = LieferAdresse.Builder().setKundenName("Kim")
                                                .setStraßeNummer("Allmannstr 24")
                                                .setPostleitzahl("45968")
                                                .setOrt("Gladbeck")
                                                .build();

        Bestellung bestellung = Bestellung.Builder().setProduktListe(liste)
                                                        .setLieferAdresse(adresse)
                                                        .build();

        Zahlung zahlung = Zahlung.Builder().setBestellung(bestellung).build();

        assertThat(zahlung.getBetrag().getBetrag()).isEqualTo(BigDecimal.valueOf(20.0));
    }
}
