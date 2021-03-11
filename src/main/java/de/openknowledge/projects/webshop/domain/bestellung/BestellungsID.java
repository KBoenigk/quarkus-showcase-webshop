package de.openknowledge.projects.webshop.domain.bestellung;

import java.util.Objects;
import java.util.UUID;

public class BestellungsID {

    private String id;

    public BestellungsID() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestellungsID bestellungsID = (BestellungsID) o;
        return id.equals(bestellungsID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BestellungsID{" +
                "id='" + id + '\'' +
                '}';
    }
}
