package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import de.openknowledge.projects.webshop.domain.bestellung.*;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class LieferAdresse {

    @NotNull
    private KundenName name;

    @NotNull
    private StraßeNummer straßeNummer;

    @NotNull
    private Postleitzahl plz;

    @NotNull
    private Ort ort;

    private LieferAnmerkung lieferAnmerkung;

    public LieferAdresse(Builder b) {
        this.name = b.name;
        this.straßeNummer = b.straßeNummer;
        this.plz = b.plz;
        this.ort = b.ort;
        this.lieferAnmerkung = b.lieferAnmerkung;
    }

    public KundenName getKundenName() {
        return name;
    }

    public StraßeNummer getStraßeNummer() {
        return straßeNummer;
    }

    public Postleitzahl getPlz() {
        return plz;
    }

    public Ort getOrt() {
        return ort;
    }

    public LieferAnmerkung getLieferAnmerkung() {
        return lieferAnmerkung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieferAdresse that = (LieferAdresse) o;
        return name.equals(that.name) && straßeNummer.equals(that.straßeNummer) && plz.equals(that.plz) && ort.equals(that.ort) && Objects.equals(lieferAnmerkung, that.lieferAnmerkung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, straßeNummer, plz, ort, lieferAnmerkung);
    }

    @Override
    public String toString() {
        return "LieferAdresse{" +
                "name=" + name +
                ", straßeNummer=" + straßeNummer +
                ", plz=" + plz +
                ", ort=" + ort +
                ", lieferAnmerkung=" + lieferAnmerkung +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private KundenName name;
        private StraßeNummer straßeNummer;
        private Postleitzahl plz;
        private Ort ort;
        private LieferAnmerkung lieferAnmerkung;

        private Builder() {}

        public Builder setKundenName(@NotNull String name) {
            this.name = new KundenName(name);

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

        public Builder setLieferAnmerkung(String anmerkung) {
            this.lieferAnmerkung = new LieferAnmerkung(anmerkung);

            return this;
        }

        /**
         * baut eine Instanz von LieferAdresse, wenn die Felder {@link #name}, {@link #straßeNummer},
         * {@link #plz} und {@link #ort} befüllt sind
         *
         * @return LieferAdresse
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public LieferAdresse build() {
            if (this.name == null) {
                throw new ValidationException("LieferAdresse.Builder: Kundenname darf nicht null sein!");
            }
            if (this.straßeNummer == null) {
                throw new ValidationException("LieferAdresse.Builder: Straße und Hausnummer dürfen nicht null sein!");
            }
            if (this.plz == null) {
                throw new ValidationException("LieferAdresse.Builder: Postleitzahl darf nicht null sein!");
            }
            if (this.ort == null) {
                throw new ValidationException("LieferAdresse.Builder: Ort darf nicht null sein!");
            }

            return new LieferAdresse(this);
        }
    }
}
