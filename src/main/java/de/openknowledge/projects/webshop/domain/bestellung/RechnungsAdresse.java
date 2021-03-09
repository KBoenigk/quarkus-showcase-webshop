package de.openknowledge.projects.webshop.domain.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RechnungsAdresse {

    @NotNull
    private KundenName name;

    private FirmenName firmenName;

    @NotNull
    private StraßeNummer straßeNummer;

    @NotNull
    private Postleitzahl plz;

    @NotNull
    private Ort ort;

    public RechnungsAdresse(Builder b) {
        this.name = b.name;
        this.firmenName = b.firmenName;
        this.straßeNummer = b.straßeNummer;
        this.plz = b.plz;
        this.ort = b.ort;
    }

    public KundenName getName() {
        return name;
    }

    public FirmenName getFirmenName() {
        return firmenName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RechnungsAdresse that = (RechnungsAdresse) o;
        return name.equals(that.name) && Objects.equals(firmenName, that.firmenName) && straßeNummer.equals(that.straßeNummer) && plz.equals(that.plz) && ort.equals(that.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firmenName, straßeNummer, plz, ort);
    }

    @Override
    public String toString() {
        return "RechnungsAdresse{" +
                "name=" + name +
                ", firmenName=" + firmenName +
                ", straßeNummer=" + straßeNummer +
                ", plz=" + plz +
                ", ort=" + ort +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private KundenName name;
        private FirmenName firmenName;
        private StraßeNummer straßeNummer;
        private Postleitzahl plz;
        private Ort ort;

        private Builder() {}

        public Builder setKundenName(@NotNull String name) {
            this.name = new KundenName(name);

            return this;
        }

        public Builder setFirmenName(String firmenName) {
            this.firmenName = new FirmenName(firmenName);

            return this;
        }

        public  Builder setStraßBuilder(@NotNull String straßeNummer) {
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
         * baut eine Instanz von RechnungsAdresse, wenn die Felder {@link #name}, {@link #straßeNummer},
         * {@link #plz} und {@link #ort} befüllt sind
         *
         * @return RechnungsAdresse
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public RechnungsAdresse build() {
            if (this.name == null) {
                throw new ValidationException("RechnungsAdresse.Builder: Kundenname darf nicht null sein!");
            }
            if (this.straßeNummer == null) {
                throw new ValidationException("RechnungsAdresse.Builder: Straße und Hausnummer dürfen nicht null sein!");
            }
            if (this.plz == null) {
                throw new ValidationException("RechnungsAdresse.Builder: Postleitzahl darf nicht null sein!");
            }
            if (this.ort == null) {
                throw new ValidationException("RechnungsAdresse.Builder: Ort darf nicht null sein!");
            }

            return new RechnungsAdresse(this);
        }
    }
}
