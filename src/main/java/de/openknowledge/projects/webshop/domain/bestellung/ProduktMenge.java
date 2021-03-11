package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Min;
import java.util.Objects;

public class ProduktMenge {
    private int value;

    public ProduktMenge(@Min(1) int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktMenge that = (ProduktMenge) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProduktMenge{" +
                "value=" + value +
                '}';
    }
}
