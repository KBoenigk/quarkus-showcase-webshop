package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Lieferung {

    @NotNull
    private final Bestellung bestellung;

    @NotNull
    private LieferZeitpunkt lieferZeitpunkt;

    private LieferStart lieferStart;

    private LieferEnde lieferEnde;

    public Lieferung(Builder b) {
        this.bestellung = b.bestellung;
        this.lieferZeitpunkt = b.lieferZeitpunkt;
        this.lieferStart = b.lieferStart;
        this.lieferEnde = b.lieferEnde;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public LieferZeitpunkt getLieferZeitpunkt() {
        return lieferZeitpunkt;
    }

    public LieferStart getLieferStart() {
        return lieferStart;
    }

    public LieferEnde getLieferEnde() {
        return lieferEnde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieferung lieferung = (Lieferung) o;
        return bestellung.equals(lieferung.bestellung) && lieferZeitpunkt.equals(lieferung.lieferZeitpunkt)
                && lieferStart.equals(lieferung.lieferStart) && lieferEnde.equals(lieferung.lieferEnde);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bestellung, lieferZeitpunkt, lieferStart, lieferEnde);
    }

    @Override
    public String toString() {
        return "Lieferung{" +
                "bestellung=" + bestellung +
                ", lieferZeitpunkt=" + lieferZeitpunkt +
                ", lieferStart=" + lieferStart +
                ", lieferEnde=" + lieferEnde +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private Bestellung bestellung;
        private LieferZeitpunkt lieferZeitpunkt;
        private LieferStart lieferStart;
        private LieferEnde lieferEnde;

        private Builder() {}

        public Builder setBestellung(@NotNull Bestellung bestellung) {
            this.bestellung = bestellung;

            return this;
        }

        public Builder setLieferZeitpunkt(@NotNull LieferZeitpunkt lieferZeitpunkt) {
            this.lieferZeitpunkt = lieferZeitpunkt;

            return this;
        }

        public Builder setLieferStart(@NotNull LieferStart lieferStart) {
            this.lieferStart = lieferStart;

            return this;
        }

        public Builder setLieferEnde(@NotNull LieferEnde lieferEnde) {
            this.lieferEnde = lieferEnde;

            return this;
        }

        /**
         * baut eine Instanz von Lieferung, wenn die Felder {@link #bestellung} und {@link #lieferZeitpunkt} befüllt sind
         *
         * @return Lieferung
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public Lieferung build() {
            if (this.bestellung == null) {
                throw new ValidationException("Lieferung.Builder: Bestellung darf nicht null sein!");
            }
            if (this.lieferZeitpunkt == null) {
                throw new ValidationException("Lieferung.Builder: Lieferzeitpunkt darf nicht null sein!");
            }
            return new Lieferung(this);
        }
    }
}
