package de.openknowledge.projects.webshop.application.bestellung;

import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Ein DTO, das eine Lieferadresse repräsentiert
 */
public class LieferAdresseDTO {

    @NotNull
    private String name;

    @NotNull
    private String strasseNummer;

    @NotNull
    private String plz;

    @NotNull
    private String ort;

    protected LieferAdresseDTO() { super(); }

    public LieferAdresseDTO(final String name, final String strasseNummer,
                            final String plz, final String ort) {
        this();
        this.name = Validate.notNull(name, "Name darf nicht null sein.");
        this.strasseNummer = Validate.notNull(name, "Strasse mit Nummer darf nicht null sein.");
        this.plz = Validate.notNull(plz, "Postleitzahl darf nicht null sein.");
        this.ort = Validate.notNull(ort, "Ort darf nicht null sein.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasseNummer() {
        return strasseNummer;
    }

    public void setStrasseNummer(String strasseNummer) {
        this.strasseNummer = strasseNummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieferAdresseDTO that = (LieferAdresseDTO) o;
        return name.equals(that.name) && strasseNummer.equals(that.strasseNummer) && plz.equals(that.plz) && ort.equals(that.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strasseNummer, plz, ort);
    }

    @Override
    public String toString() {
        return "LieferAdresseDTO{" +
                "name='" + name + '\'' +
                ", strasseNummer='" + strasseNummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
