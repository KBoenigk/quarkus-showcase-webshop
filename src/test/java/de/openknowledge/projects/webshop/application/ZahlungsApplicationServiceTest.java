package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.zahlung.ZahlungsApplicationService;
import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import de.openknowledge.projects.webshop.infrastructure.zahlung.ZahlungsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ZahlungsApplicationServiceTest {

    @InjectMocks
    private ZahlungsApplicationService zahlungsService;

    @Mock
    private ZahlungsRepository zahlungsRepository;

    private Zahlung zahlung;

    @BeforeEach
    public void setUp() {

        Produkt bowl = Produkt.Builder().setProduktName("Bowl").setProduktPreis(BigDecimal.valueOf(8.4)).build();

        ProduktListenElement bowlElement = ProduktListenElement.Builder()
                .setProdukt(bowl)
                .setProduktMenge(2).build();

        ProduktListe produktListe = ProduktListe.Builder()
                .addProdukt(bowlElement)
                .build();

        LieferAdresse lieferAdresse = LieferAdresse.Builder()
                                        .setKundenName("Kim")
                                        .setStraßeNummer("Allmannstr. 24")
                                        .setPostleitzahl("45968")
                                        .setOrt("Gladbeck").build();

        Bestellung bestellung = Bestellung.Builder().setProduktListe(produktListe)
                                                    .setLieferAdresse(lieferAdresse)
                                                    .build();

        this.zahlung = Zahlung.Builder().setBestellung(bestellung)
                                        .build();
    }

    @Test
    public void autorisiereZahlungShouldAuthorizeZahlungInRepository() {
//        Mockito.when(this.zahlungsRepository.findZahlungByID(this.zahlung.getZahlungsId())).thenReturn(Optional.of(this.zahlung));
//
//        this.zahlungsService.autorisiereZahlung(this.autorisierung);
//
//        Assertions.assertThat(this.zahlungsRepository.read().get(0).getAutorisierung().isAutorisiert()).isTrue();
    }
}
