package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListe;
import de.openknowledge.projects.webshop.domain.bestellung.ProduktListenElement;
import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;
import de.openknowledge.projects.webshop.infrastructure.bestellung.BestellungRepository;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BestellungApplicationService {
    @Inject
    private BestellungRepository bestellungRepository;

    @Inject
    private ProduktRepository produktRepository;

    public BestellungApplicationService() { super(); }

    public List<Bestellung> getBestellungen() {
        return bestellungRepository.read();
    }

    public void placeBestellung(BestellungDTO bestellungDTO) {

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

        this.bestellungRepository.putBestellung(bestellung);
    }
}
