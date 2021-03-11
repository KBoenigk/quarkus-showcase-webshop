package de.openknowledge.projects.webshop.domain.zahlung;

import java.util.Objects;

public class ZahlungsArt {
    private String value;

    public ZahlungsArt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsArt that = (ZahlungsArt) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
