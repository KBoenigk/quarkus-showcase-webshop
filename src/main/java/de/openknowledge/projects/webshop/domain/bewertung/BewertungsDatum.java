package de.openknowledge.projects.webshop.domain.bewertung;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Objects;

public class BewertungsDatum {
    private Date value;

    public BewertungsDatum(@NotNull @Past Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BewertungsDatum that = (BewertungsDatum) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BewertungsDatum{" +
                "value=" + value +
                '}';
    }
}
