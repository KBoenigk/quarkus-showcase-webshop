package de.openknowledge.projects.webshop.application.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import de.openknowledge.projects.webshop.infrastructure.bestellung.BestellRepository;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
import de.openknowledge.projects.webshop.infrastructure.zahlung.ZahlungsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Bestellungen
 */
@ApplicationScoped
public class BestellApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(BestellApplicationService.class);

    private BestellRepository bestellRepository;

    private ProduktRepository produktRepository;

    private ZahlungsRepository zahlungsRepository;

    @Inject
    public BestellApplicationService(BestellRepository bestellRepository, ProduktRepository produktRepository, ZahlungsRepository zahlungsRepository) {
        this.bestellRepository = bestellRepository;
        this.produktRepository = produktRepository;
        this.zahlungsRepository = zahlungsRepository;
    }

    /**
     * Gibt alle Bestellungen zurück
     * @return Bestellungen alle eingegangenen Bestellungen
     */
    public List<Bestellung> getBestellungen() {
        return bestellRepository.read();
    }

    /**
     * Wandelt das DTO um und erzeugt eine neue Bestellung, die dem Repository hinzugefügt wird
     * @param bestellungDTO getätigte Bestellung
     * @return Zahlungsaufforderung zur getätigten Bestellung
     */
    public ZahlungsAufforderungDTO placeBestellung(BestellungDTO bestellungDTO) {

        Bestellung bestellung = this.convertBestellung(bestellungDTO);

        this.bestellRepository.putBestellung(bestellung);

        LOG.info("Bestellung placed {}", bestellung);

        Zahlung zahlung = this.createZahlung(bestellung);

        LOG.info("Zahlung created {}", zahlung);

        this.zahlungsRepository.putZahlung(zahlung);

        ZahlungsAufforderungDTO aufforderung = createZahlungsAufforderung(bestellung, zahlung);

        LOG.info("Zahlungsaufforderung created {}", aufforderung);

        return aufforderung;
    }

    /**
     * Konvertiert ein BestellungDTO-Objekt in ein Bestellung-Objekt
     * @param bestellungDTO umzuwandelnde Bestellung
     * @return umgewandelte Bestellung
     */
    private Bestellung convertBestellung(BestellungDTO bestellungDTO) {
        List<ProduktAuswahlDTO> produktAuswahl = bestellungDTO.getProduktAuswahl();
        ProduktListe.Builder produktListeBuilder = ProduktListe.Builder();

        produktAuswahl.forEach(auswahl -> {
            Optional<Produkt> optional = produktRepository.findByProduktName(auswahl.getName());

            if (!optional.isPresent()) {
                throw new ValidationException("Das Produkt mit dem Namen " + auswahl.getName() + " wird nicht angeboten!");
            }

            ProduktListenElement element = ProduktListenElement.Builder()
                    .setProdukt(optional.get())
                    .setProduktMenge(auswahl.getMenge())
                    .build();
            produktListeBuilder.addProdukt(element);
        });

        LieferAdresse lieferAdresse = LieferAdresse.Builder().setKundenName(bestellungDTO.getLieferAdresse().getName())
                .setStraßeNummer(bestellungDTO.getLieferAdresse().getStrasseNummer())
                .setPostleitzahl(bestellungDTO.getLieferAdresse().getPlz())
                .setOrt(bestellungDTO.getLieferAdresse().getOrt()).build();

        Bestellung bestellung = Bestellung.Builder().setProduktListe(produktListeBuilder.build())
                .setLieferAdresse(lieferAdresse)
                .build();

        return bestellung;
    }

    /**
     * Erstellt ein Zahlungs-Objekt mit der übergebenen Bestellung
     * @param bestellung Bestellung, zu der eine Zahlung erstellt werden soll
     * @return ein neues Zahlung-Objekt
     */
    private Zahlung createZahlung(Bestellung bestellung) {
        Zahlung zahlung = Zahlung.Builder()
                .setBestellung(bestellung)
                .build();

        return zahlung;
    }

    /**
     * Erzeugt ein neues DTO Zahlungsaufforderung mit der übergebenen Bestellung und Zahlung
     * @param bestellung bestellung zu der eine Zahlungsaufforderung erstellt werden soll
     * @param zahlung Zahlung zu der eine Zahlungsaufforderung erstellt werden soll
     * @return ein neues ZahlungsAufforderungDTO
     */
    private ZahlungsAufforderungDTO createZahlungsAufforderung(Bestellung bestellung, Zahlung zahlung) {
        return new ZahlungsAufforderungDTO(bestellung.getId().getId(),
                                            zahlung.getZahlungsId().getId(),
                                            zahlung.getBetrag().getBetrag().doubleValue());
    }
}
