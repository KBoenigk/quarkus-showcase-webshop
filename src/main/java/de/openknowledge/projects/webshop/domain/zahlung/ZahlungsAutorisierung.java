package de.openknowledge.projects.webshop.domain.zahlung;

import java.util.Objects;

public class ZahlungsAutorisierung {
    private boolean value;

    public ZahlungsAutorisierung(boolean value) {
        this.value = value;
    }

    public boolean isAutorisiert() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsAutorisierung that = (ZahlungsAutorisierung) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ZahlungsAutorisierung{" +
                "value=" + value +
                '}';
    }
}
