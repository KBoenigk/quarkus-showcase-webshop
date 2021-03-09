package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;

public class ProduktBeschreibung {
    private String value;

    public ProduktBeschreibung(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
