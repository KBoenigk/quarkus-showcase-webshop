package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Min;

public class ProduktMenge {
    private int value;

    public ProduktMenge(@Min(1) int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
