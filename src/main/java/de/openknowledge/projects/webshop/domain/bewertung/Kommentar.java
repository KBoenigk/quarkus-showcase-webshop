package de.openknowledge.projects.webshop.domain.bewertung;

import javax.validation.constraints.NotNull;

public class Kommentar {
    private String value;

    public Kommentar(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
