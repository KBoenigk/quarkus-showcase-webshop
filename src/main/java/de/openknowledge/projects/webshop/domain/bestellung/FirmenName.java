package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;

public class FirmenName {
    private String value;

    public FirmenName(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
