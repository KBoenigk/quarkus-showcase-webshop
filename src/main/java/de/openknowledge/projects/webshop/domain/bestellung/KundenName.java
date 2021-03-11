package de.openknowledge.projects.webshop.domain.bestellung;

import java.util.Objects;

public class KundenName {
    private String value;

    public KundenName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KundenName that = (KundenName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "KundenName{" +
                "value='" + value + '\'' +
                '}';
    }
}
