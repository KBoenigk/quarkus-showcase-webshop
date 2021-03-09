package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class LieferAnmerkung {
    private String value;

    public LieferAnmerkung(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieferAnmerkung that = (LieferAnmerkung) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LieferAnmerkung{" +
                "value='" + value + '\'' +
                '}';
    }
}
