package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ProduktPreis {
    private BigDecimal preis;

    public ProduktPreis(@NotNull BigDecimal preis) {
        this.preis = preis;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktPreis that = (ProduktPreis) o;
        return preis.equals(that.preis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preis);
    }

    @Override
    public String toString() {
        return "ProduktPreis{" +
                "preis=" + preis +
                '}';
    }
}
