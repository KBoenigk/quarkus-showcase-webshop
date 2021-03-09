package de.openknowledge.projects.webshop.domain.bestellung.filiale;

import de.openknowledge.projects.webshop.domain.bestellung.Ort;
import de.openknowledge.projects.webshop.domain.bestellung.Postleitzahl;
import de.openknowledge.projects.webshop.domain.bestellung.StraßeNummer;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class FilialAdresse {

    @NotNull
    private FilialAdressName name;

    @NotNull
    private StraßeNummer straßeNummer;

    @NotNull
    private Postleitzahl plz;

    @NotNull
    private Ort ort;

    private FilialAdresse(Builder b) {
        this.name = b.name;
        this.straßeNummer = b.straßeNummer;
        this.plz = b.plz;
        this.ort = b.ort;
    }

    public FilialAdressName getFilialAdressName() {
        return name;
    }

    public StraßeNummer getStrasseNummer() {
        return straßeNummer;
    }

    public Postleitzahl getPlz() {
        return plz;
    }

    public Ort getOrt() {
        return ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilialAdresse that = (FilialAdresse) o;
        return name.equals(that.name) && straßeNummer.equals(that.straßeNummer) && plz.equals(that.plz) && ort.equals(that.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, straßeNummer, plz, ort);
    }

    @Override
    public String toString() {
        return "FilialAdresse{" +
                    "name=" + name +
                    ", straßeNummer=" + straßeNummer +
                    ", plz=" + plz +
                    ", ort=" + ort +
                    '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private FilialAdressName name;

        private StraßeNummer straßeNummer;

        private Postleitzahl plz;

        private Ort ort;

        private Builder() {}

        public Builder setFilialAdressName(@NotNull String name) {
            this.name = new FilialAdressName(name);
            return this;
        }

        public Builder setStraßeNummer(@NotNull String straßeNummer) {
            this.straßeNummer = new StraßeNummer(straßeNummer);
            return this;
        }

        public Builder setPostleitzahl(@NotNull String plz) {
            this.plz = new Postleitzahl(plz);
            return this;
        }

        public Builder setOrt(@NotNull String ort) {
            this.ort = new Ort(ort);
            return this;
        }

        /**
         * baut eine Instanz von FilialAdresse, wenn die Felder {@link #name}, {@link #straßeNummer},
         * {@link #plz} und {@link #ort} befüllt sind
         *
         * @return FilialAdresse
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public FilialAdresse build() {
            if (this.name == null) {
                throw new ValidationException("FilialAdresse.Builder: Name darf nicht null sein!");
            }
            if (this.straßeNummer == null) {
                throw new ValidationException("FilialAdresse: Straße mit Nummer darf nicht null sein!");
            }
            if (this.plz == null) {
                throw new ValidationException("FilialAdresse: Postleitzahl darf nicht null sein!");
            }
            if (this.ort == null) {
                throw new ValidationException("FilialAdresse: Ort darf nicht null sein!");
            }

            return new FilialAdresse(this);
        }
    }
}
