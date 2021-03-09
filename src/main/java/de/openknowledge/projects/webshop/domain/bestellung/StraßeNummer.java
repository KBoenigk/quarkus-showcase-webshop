package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;

public class StraßeNummer {
    private String value;

    public StraßeNummer(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
