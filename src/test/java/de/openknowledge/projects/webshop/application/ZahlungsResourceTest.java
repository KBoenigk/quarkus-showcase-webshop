package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.zahlung.ZahlungsApplicationService;
import de.openknowledge.projects.webshop.application.zahlung.ZahlungsResource;
import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ZahlungsResourceTest {

    @InjectMocks
    private ZahlungsResource resource;

    @Mock
    private ZahlungsApplicationService zahlungsService;

    private List<Zahlung> zahlungen;

    @BeforeEach
    public void setUp() {
        this.zahlungen = new ArrayList<>();

        Produkt bowl = Produkt.Builder().setProduktName("Bowl").setProduktPreis(BigDecimal.valueOf(8.4)).build();

        Produkt salat = Produkt.Builder().setProduktName("Salat").setProduktPreis(BigDecimal.valueOf(5.5)).build();

        Produkt sticks = Produkt.Builder().setProduktName("Sticks").setProduktPreis(BigDecimal.valueOf(1)).build();

        ProduktListenElement bowlElement = ProduktListenElement.Builder()
                .setProdukt(bowl)
                .setProduktMenge(2).build();
        ProduktListenElement salatElement = ProduktListenElement.Builder()
                .setProdukt(salat)
                .setProduktMenge(1).build();
        ProduktListenElement sticksElement = ProduktListenElement.Builder()
                .setProdukt(sticks)
                .setProduktMenge(3).build();
        ProduktListe produktListe = ProduktListe.Builder()
                .addProdukt(bowlElement)
                .addProdukt(salatElement)
                .addProdukt(sticksElement)
                .build();
        LieferAdresse lieferAdresse = LieferAdresse.Builder()
                .setKundenName("Kim")
                .setStraßeNummer("Allmannstr. 24")
                .setPostleitzahl("45968")
                .setOrt("Gladbeck").build();

        Bestellung bestellung = Bestellung.Builder().setProduktListe(produktListe)
                                                    .setLieferAdresse(lieferAdresse)
                                                    .build();

        Zahlung zahlung1 = Zahlung.Builder().setBestellung(bestellung).build();

        Zahlung zahlung2 = Zahlung.Builder().setBestellung(bestellung).build();

        this.zahlungen.add(zahlung1);
        this.zahlungen.add(zahlung2);
    }

    @Test
    public void autorisiereZahlungShouldReturn201() {

        Response response = this.resource.autorisiereZahlung("1234");

        Assertions.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    }

    @Test
    public void getTahlungenShouldReturn2Zahlungen() {
        Mockito.doReturn(this.zahlungen).when(this.zahlungsService).getZahlungen();

        Response response = this.resource.getZahlungen();
        List<Zahlung> returnedZahlungen = (List<Zahlung>) response.getEntity();

        Assertions.assertThat(returnedZahlungen.size()).isEqualTo(2);
    }
}
