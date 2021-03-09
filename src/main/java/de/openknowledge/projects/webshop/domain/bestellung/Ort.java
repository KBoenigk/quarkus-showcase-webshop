package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;

public class Ort {
    private String value;

    public Ort(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
