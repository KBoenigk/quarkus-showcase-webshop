package de.openknowledge.projects.webshop.domain.zahlung;

import java.util.Objects;
import java.util.UUID;

public class ZahlungsID {
    private String id;

    public ZahlungsID() {
        this.id = UUID.randomUUID().toString();
    }
    public ZahlungsID(String id) {this.id = id;}

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsID zahlungsID = (ZahlungsID) o;
        return id.equals(zahlungsID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ZahlungsID{" +
                "id='" + id + '\'' +
                '}';
    }
}
