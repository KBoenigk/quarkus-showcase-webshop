package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProduktPreis {
    private BigDecimal preis;

    public ProduktPreis(@NotNull BigDecimal preis) {
        this.preis = preis;
    }

    public BigDecimal getPreis() {
        return preis;
    }
}
