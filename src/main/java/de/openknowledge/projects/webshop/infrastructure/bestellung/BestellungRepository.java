package de.openknowledge.projects.webshop.infrastructure.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class BestellungRepository {

    private final List<Bestellung> bestellungen = new ArrayList<>();

    public void putBestellung(Bestellung bestellung) {
        this.bestellungen.add(bestellung);
    }

    public List<Bestellung> read() {return Collections.unmodifiableList(this.bestellungen); }
}
