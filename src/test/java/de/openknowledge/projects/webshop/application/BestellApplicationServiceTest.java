package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.bestellung.*;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
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

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BestellApplicationServiceTest {
    //Konvertierung BestellungDTO -> Bestellung

    // Zahlung erstellen
    // Zahlung in Repository hinzufügen
    // Zahlungsaufforderung erstellen
    private BestellungDTO bestellungDTO;

    private Produkt produkt;

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
        produktAuswahlDTOListe.add(new ProduktAuswahlDTO("Rosinenbomber Bowl", 2));

        LieferAdresseDTO lieferAdresseDTO = new LieferAdresseDTO("Kim",
                                                            "Allmannstr. 24",
                                                                    "45968", "Gladbeck");

        this.bestellungDTO = new BestellungDTO(produktAuswahlDTOListe, lieferAdresseDTO);

        this.produkt = Produkt.Builder().setProduktName("Bowl").setProduktPreis(BigDecimal.valueOf(8.4)).build();


    }


    @Test
    public void placeBestellungShouldReturnZahlungsAufforderung() {
        Mockito.doReturn(Optional.of(this.produkt)).when(produktRepository).findByProduktName(Mockito.anyString());

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        Assertions.assertThat(zahlungsAufforderungDTO).isInstanceOf(ZahlungsAufforderungDTO.class);
    }

    // Bestellung in Repository hinzufügen
    @Test
    public void placeBestellungShouldAddOneBestellungToBestellRepository() {
        Mockito.doReturn(Optional.of(this.produkt)).when(produktRepository).findByProduktName(Mockito.anyString());

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        Assertions.assertThat(this.bestellRepository.read().size()).isEqualTo(1);
    }

    @Test
    public void placeBestellungShouldAddOneZahlungToZahlungsRepository() {
        Mockito.doReturn(Optional.of(this.produkt)).when(produktRepository).findByProduktName(Mockito.anyString());

        ZahlungsAufforderungDTO zahlungsAufforderungDTO = this.bestellService.placeBestellung(this.bestellungDTO);

        Assertions.assertThat(this.zahlungsRepository.read().size()).isEqualTo(1);
    }
}
