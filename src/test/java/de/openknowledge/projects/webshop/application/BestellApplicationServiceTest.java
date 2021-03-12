package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.bestellung.*;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.infrastructure.bestellung.BestellRepository;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
import de.openknowledge.projects.webshop.infrastructure.zahlung.ZahlungsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BestellApplicationServiceTest {

    private BestellungDTO bestellungDTO;

    private Produkt bowl;

    private Produkt salat;

    private Produkt sticks;

    private ProduktListe produktListe;

    private LieferAdresse lieferAdresse;

    @InjectMocks
    private BestellApplicationService bestellService;

    @Mock
    private ProduktRepository produktRepository;

    private BestellRepository bestellRepository;

    private ZahlungsRepository zahlungsRepository;

    @BeforeEach
    public void setUp() {

        this.bestellRepository = new BestellRepository();

        this.zahlungsRepository = new ZahlungsRepository();

        this.bestellService = new BestellApplicationService(this.bestellRepository, this.produktRepository, this.zahlungsRepository);

        List<ProduktAuswahlDTO> produktAuswahlDTOListe = new ArrayList<>();
        produktAuswahlDTOListe.add(new ProduktAuswahlDTO("Bowl", 2));
        produktAuswahlDTOListe.add(new ProduktAuswahlDTO("Salat", 1));
        produktAuswahlDTOListe.add(new ProduktAuswahlDTO("Sticks", 3));

        LieferAdresseDTO lieferAdresseDTO = new LieferAdresseDTO("Kim",
                                                            "Allmannstr. 24",
                                                                    "45968", "Gladbeck");

        this.bestellungDTO = new BestellungDTO(produktAuswahlDTOListe, lieferAdresseDTO);

        this.bowl = Produkt.Builder().setProduktName("Bowl").setProduktPreis(BigDecimal.valueOf(8.4)).build();

        this.salat = Produkt.Builder().setProduktName("Salat").setProduktPreis(BigDecimal.valueOf(5.5)).build();

        this.sticks = Produkt.Builder().setProduktName("Sticks").setProduktPreis(BigDecimal.valueOf(1)).build();

        ProduktListenElement bowlElement = ProduktListenElement.Builder()
                                            .setProdukt(this.bowl)
                                            .setProduktMenge(2).build();
        ProduktListenElement salatElement = ProduktListenElement.Builder()
                                            .setProdukt(this.salat)
                                            .setProduktMenge(1).build();
        ProduktListenElement sticksElement = ProduktListenElement.Builder()
                                            .setProdukt(this.sticks)
                                            .setProduktMenge(3).build();
        this.produktListe = ProduktListe.Builder()
                                            .addProdukt(bowlElement)
                                            .addProdukt(salatElement)
                                            .addProdukt(sticksElement)
                                            .build();
        this.lieferAdresse = LieferAdresse.Builder()
                                            .setKundenName("Kim")
                                            .setStraßeNummer("Allmannstr. 24")
                                            .setPostleitzahl("45968")
                                            .setOrt("Gladbeck").build();
    }


    @Test
    public void placeBestellungShouldReturnZahlungsAufforderung() {
        Mockito.when(produktRepository.findByProduktName(Mockito.anyString())).thenReturn(Optional.of(bowl),
                                                                                            Optional.of(salat),
                                                                                            Optional.of(sticks));

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        // Es wird ein Objekt vom Typ ZahlungsAufforderungDTO zurückgegeben
        Assertions.assertThat(zahlungsAufforderungDTO).isInstanceOf(ZahlungsAufforderungDTO.class);
    }

    @Test
    public void placeBestellungShouldAddOneCorrectBestellungToBestellRepository() {
        Mockito.when(produktRepository.findByProduktName(Mockito.anyString())).thenReturn(Optional.of(bowl),
                                                                                            Optional.of(salat),
                                                                                            Optional.of(sticks));

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        // Es wird eine Bestellung ins Repository hinzugefügt
        Assertions.assertThat(this.bestellRepository.read().size()).isEqualTo(1);

        // Die Bestellung im Repository wurde korrekt erstellt
        Assertions.assertThat(this.bestellRepository.read().get(0).getProdukte()).isEqualTo(this.produktListe);
        Assertions.assertThat(this.bestellRepository.read().get(0).getLieferAdresse()).isEqualTo(this.lieferAdresse);
    }

    @Test
    public void placeBestellungShouldAddOneCorrectZahlungToZahlungsRepository() {
        Mockito.when(produktRepository.findByProduktName(Mockito.anyString())).thenReturn(Optional.of(bowl),
                                                                                            Optional.of(salat),
                                                                                            Optional.of(sticks));

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        // Es wird eine Zahlung ins Repository hinzugefügt
        Assertions.assertThat(this.zahlungsRepository.read().size()).isEqualTo(1);

        // Die Zahlung im Repository wurde korrekt erstellt
        Assertions.assertThat(this.zahlungsRepository.read().get(0).getBestellung()).isEqualTo(this.bestellRepository.read().get(0));
    }

    @Test
    public void betragOfZahlungShouldBeCalculatedCorrectly() {
        Mockito.when(produktRepository.findByProduktName(Mockito.anyString())).thenReturn(Optional.of(bowl),
                                                                                            Optional.of(salat),
                                                                                            Optional.of(sticks));

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        // Der Betrag in der Zahlung wurde korrekt berechnet
        Assertions.assertThat(this.zahlungsRepository.read().get(0).getBetrag().getBetrag()).isEqualTo(BigDecimal.valueOf(25.3));

        // Der Betrag in der ZahlungsafforderungDTO wurde korrekt berechnet
        Assertions.assertThat(zahlungsAufforderungDTO.getBetrag()).isEqualTo(25.3);
    }
}
