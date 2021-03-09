package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class Betrag {
    private BigDecimal value;

    public Betrag(@Min(0) BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
