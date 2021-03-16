package de.openknowledge.projects.webshop.application.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import de.openknowledge.projects.webshop.domain.zahlung.ZahlungsAutorisierung;
import de.openknowledge.projects.webshop.domain.zahlung.ZahlungsID;
import de.openknowledge.projects.webshop.infrastructure.zahlung.ZahlungsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Zahlungen
 */
@ApplicationScoped
public class ZahlungsApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(ZahlungsApplicationService.class);

    @Inject
    private ZahlungsRepository zahlungsRepository;

    public ZahlungsApplicationService() { super(); }

    /**
     * Gibt alle Zahlungen zurück
     * @return alle Zahlungen
     */
    public List<Zahlung> getZahlungen() { return this.zahlungsRepository.read(); }

    /**
     * Autorisiert die Zahlung mit der zahlungs ID der übergebenen Zahlungsautorisierung
     * @param zahlungsID ID der zu autorisierenden Zahlung
     */
    public void autorisiereZahlung(String zahlungsID) {

        ZahlungsID id = new ZahlungsID(zahlungsID);
        Optional<Zahlung> optional
                = this.zahlungsRepository.findZahlungByID(id);

        if (!optional.isPresent()) {
            throw new ValidationException("Eine Zahlung mit der ID " + id + " existiert nicht.");
        }

        Zahlung zahlung = optional.get();
        zahlung.setAutorisierung(new ZahlungsAutorisierung(true));

        this.zahlungsRepository.updateZahlung(zahlung);
    }
}
