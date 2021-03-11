package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.bestellung.ProduktResource;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
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

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ProduktResourceTest {

    // Resource, in die der Mock injected werden soll
    @InjectMocks
    private ProduktResource resource;

    // Repository das  gemocked werden soll
    @Mock
    private ProduktRepository repository;

    private List<Produkt> produktListeIn;

    private List<Produkt> produktListeOut;

    @BeforeEach
    public void setUp() {

        Produkt produkt = Produkt.Builder().setProduktName("Bowl")
                .setProduktPreis(BigDecimal.valueOf(10.0))
                .build();

        this.produktListeIn = new ArrayList<>();
        produktListeIn.add(produkt);

        this.produktListeOut = new ArrayList<>();
        produktListeOut.add(produkt);
    }

    @Test
    public void getProduktListeShouldReturnProduktListe() {
        Mockito.doReturn(this.produktListeIn).when(repository).read();

        Response response = this.resource.getProduktListe();

        List<Produkt> liste = (List<Produkt>) response.getEntity();

        assertThat(liste).isEqualTo(this.produktListeOut);

        Mockito.verify(repository).read();
        Mockito.verifyNoMoreInteractions(repository);
    }
}
