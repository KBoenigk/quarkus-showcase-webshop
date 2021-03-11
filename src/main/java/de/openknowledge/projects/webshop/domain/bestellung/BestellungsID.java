package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class BestellID {

    private String id;

    public BestellID() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestellID bestellID = (BestellID) o;
        return id.equals(bestellID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BestellID{" +
                "id='" + id + '\'' +
                '}';
    }
}
