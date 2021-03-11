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

@ApplicationScoped
public class ZahlungsApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(ZahlungsApplicationService.class);

    @Inject
    private ZahlungsRepository zahlungsRepository;

    public ZahlungsApplicationService() { super(); }

    public List<Zahlung> getZahlungen() { return this.zahlungsRepository.read(); }

    public void autorisiereZahlung(ZahlungsAutorisierungDTO autorisierung) {

        ZahlungsID id = new ZahlungsID(autorisierung.getZahlungsID());
        Optional<Zahlung> optional
                = this.zahlungsRepository.findZahlungByID(id);

        if (!optional.isPresent()) {
            throw new ValidationException("Eine Zahlung mit der ID " + id + " existiert nicht.");
        }

        optional.get().setAutorisierung(new ZahlungsAutorisierung(true));
    }
}
