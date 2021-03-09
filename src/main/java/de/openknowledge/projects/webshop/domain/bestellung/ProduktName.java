package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;

public class ProduktName {
    private String value;

    public ProduktName(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
