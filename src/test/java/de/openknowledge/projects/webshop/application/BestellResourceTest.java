package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.application.bestellung.*;
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
public class BestellResourceTest {

    @InjectMocks
    private BestellResource resource;

    @Mock
    private BestellApplicationService bestellService;

    private ZahlungsAufforderungDTO aufforderungDTO;

    private BestellungDTO bestellungDTO;

    @BeforeEach
    public void setUp() {

        this.aufforderungDTO = new ZahlungsAufforderungDTO("01234", "56789", 19.6);

        ProduktAuswahlDTO auswahlDTO = new ProduktAuswahlDTO("Salat", 2);
        List<ProduktAuswahlDTO> auswahlListeDTO = new ArrayList<>();
        auswahlListeDTO.add(auswahlDTO);

        LieferAdresseDTO lieferAdresseDTO = new LieferAdresseDTO("Kim", "Teststr. 1", "12345", "Teststadt");

        this.bestellungDTO = new BestellungDTO(auswahlListeDTO, lieferAdresseDTO);
    }

    @Test
    public void placeBestellungShouldReturn201() {
        Mockito.doReturn(this.aufforderungDTO).when(bestellService).placeBestellung(Mockito.any());

        Response response = resource.placeBestellung(this.bestellungDTO);

        Assertions.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
        Assertions.assertThat(response.getEntity()).isEqualTo(this.aufforderungDTO);
    }
}
