package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Postleitzahl {

    private String value;

    public Postleitzahl(@NotNull @Pattern(regexp="^[0-9]{5}$") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postleitzahl that = (Postleitzahl) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Postleitzahl{" +
                "value='" + value + '\'' +
                '}';
    }
}
