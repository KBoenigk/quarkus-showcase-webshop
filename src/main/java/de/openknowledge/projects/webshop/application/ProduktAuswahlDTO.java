package de.openknowledge.projects.webshop.application;

import org.apache.commons.lang3.Validate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Ein DTO, das eine Produktauswahl repräsentiert
 */
public class ProduktAuswahlDTO {

    @NotNull
    private String name;

    @Min(1)
    private int menge;

    public ProduktAuswahlDTO() { super(); }

    public ProduktAuswahlDTO(final String name, final int menge) {
        this();
        this.name = Validate.notNull(name, "Name darf nicht null sein.");

        // Validation?
        this.menge = menge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktAuswahlDTO that = (ProduktAuswahlDTO) o;
        return menge == that.menge && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, menge);
    }

    @Override
    public String toString() {
        return "ProduktAuwahlDTO{" +
                "name='" + name + '\'' +
                ", menge=" + menge +
                '}';
    }
}
