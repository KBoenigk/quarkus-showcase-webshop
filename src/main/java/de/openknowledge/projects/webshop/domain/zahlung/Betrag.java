package de.openknowledge.projects.webshop.domain.zahlung;

import java.math.BigDecimal;

public class Betrag {
    private BigDecimal value;

    public Betrag(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
