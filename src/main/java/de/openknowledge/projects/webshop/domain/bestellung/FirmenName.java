package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class FirmenName {
    private String value;

    public FirmenName(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirmenName that = (FirmenName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FirmenName{" +
                "value='" + value + '\'' +
                '}';
    }
}
