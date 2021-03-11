package de.openknowledge.projects.webshop.domain.zahlung;

import java.util.Objects;

public class ZahlungsAbschluss {
    private boolean value;

    public ZahlungsAbschluss(boolean value) {
        this.value = value;
    }

    public boolean isAbgeschlossen() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsAbschluss that = (ZahlungsAbschluss) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ZahlungsAbschluss{" +
                "value=" + value +
                '}';
    }
}
