package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class LieferZeitpunkt {
    private Date value;

    public LieferZeitpunkt(@NotNull Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieferZeitpunkt that = (LieferZeitpunkt) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LieferZeitpunkt{" +
                "value=" + value +
                '}';
    }
}
