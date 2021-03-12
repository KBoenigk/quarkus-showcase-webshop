package de.openknowledge.projects.webshop.infrastructure.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import de.openknowledge.projects.webshop.domain.zahlung.ZahlungsID;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ZahlungsRepository {
    private final List<Zahlung> zahlungen = new ArrayList<>();

    public void putZahlung(Zahlung zahlung) { this.zahlungen.add(zahlung); }

    public List<Zahlung> read() { return Collections.unmodifiableList(this.zahlungen); }

    public Optional<Zahlung> findZahlungByID(ZahlungsID id) {
        return this.zahlungen.stream().filter(zahlung -> id.equals(zahlung.getZahlungsId()))
                .findAny();
    }

    public void updateZahlung(Zahlung zahlung) {

        this.zahlungen.remove(zahlung);
        this.zahlungen.add(zahlung);
    }
}
