package de.openknowledge.projects.webshop.domain.bewertung;

import java.util.Objects;

public class Wertung {
    private int value;

    public Wertung(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertung wertung = (Wertung) o;
        return value == wertung.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Wertung{" +
                "value=" + value +
                '}';
    }
}
