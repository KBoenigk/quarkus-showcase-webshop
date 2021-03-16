package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.zahlung.ZahlungsApplicationService;
import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import de.openknowledge.projects.webshop.infrastructure.zahlung.ZahlungsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ZahlungsApplicationServiceTest {

    private ZahlungsApplicationService zahlungsService;

    private ZahlungsRepository zahlungsRepository;

    private Zahlung zahlung;

    @Captor
    private ArgumentCaptor<Zahlung> captor;

    @BeforeEach
    public void setUp() {

        this.zahlungsRepository = Mockito.mock(ZahlungsRepository.class);
        this.zahlungsService = new ZahlungsApplicationService(this.zahlungsRepository);

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

        this.zahlungsRepository.putZahlung(this.zahlung);
    }

    @Test
    public void autorisiereZahlungShouldUpdateAuthorizedZahlung() {
        Mockito.when(this.zahlungsRepository.findZahlungByID(this.zahlung.getZahlungsId())).thenReturn(Optional.of(this.zahlung));

        this.zahlungsService.autorisiereZahlung(this.zahlung.getZahlungsId().getId());

        Mockito.verify(this.zahlungsRepository).updateZahlung(captor.capture());
        Zahlung capturedArgument = captor.getValue();
        Assertions.assertThat(capturedArgument.getAutorisierung().isAutorisiert()).isTrue();
    }
}
