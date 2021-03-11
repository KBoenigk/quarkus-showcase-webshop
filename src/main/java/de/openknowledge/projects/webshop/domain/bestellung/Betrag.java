package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;

public class Betrag {
    private BigDecimal value;

    public Betrag(@Min(0) BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betrag betrag = (Betrag) o;
        return value.equals(betrag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Betrag{" +
                "value=" + value +
                '}';
    }
}
