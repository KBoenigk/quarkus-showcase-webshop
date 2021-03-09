package de.openknowledge.projects.webshop.domain.bestellung.filiale;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class FilialName {
    private String value;

    public FilialName(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilialName that = (FilialName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FilialName{" +
                "value='" + value + '\'' +
                '}';
    }
}
