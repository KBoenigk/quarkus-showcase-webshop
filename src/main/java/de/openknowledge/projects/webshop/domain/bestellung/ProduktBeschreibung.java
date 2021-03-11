package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProduktBeschreibung {
    private String value;

    public ProduktBeschreibung(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktBeschreibung that = (ProduktBeschreibung) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProduktBeschreibung{" +
                "value='" + value + '\'' +
                '}';
    }
}
