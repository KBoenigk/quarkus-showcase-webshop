package de.openknowledge.projects.webshop.domain.bewertung;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Kommentar {
    private String value;

    public Kommentar(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kommentar kommentar = (Kommentar) o;
        return value.equals(kommentar.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Kommentar{" +
                "value='" + value + '\'' +
                '}';
    }
}
