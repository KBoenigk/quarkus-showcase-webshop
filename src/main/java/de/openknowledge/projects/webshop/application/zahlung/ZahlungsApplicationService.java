package de.openknowledge.projects.webshop.infrastructure.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ZahlungsApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(ZahlungsApplicationService.class);

    @Inject
    private ZahlungsRepository zahlungsRepository;

    public ZahlungsApplicationService() { super(); }

    public List<Zahlung> getZahlungen() { return this.zahlungsRepository.read(); }
}
