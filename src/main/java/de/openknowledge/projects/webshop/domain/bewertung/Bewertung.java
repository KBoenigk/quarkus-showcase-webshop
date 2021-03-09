package de.openknowledge.projects.webshop.domain.bewertung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Bewertung {

    @NotNull
    private Filiale filiale;

    @NotNull
    private BewertungsDatum datum;

    @NotNull
    private Wertung wertung;

    private Kommentar kommentar;

    public Bewertung(Builder b) {
        this.filiale = b.filiale;
        this.datum = b.datum;
        this.wertung = b.wertung;
        this.kommentar = b.kommentar;
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public BewertungsDatum getDatum() {
        return datum;
    }

    public Wertung getWertung() {
        return wertung;
    }

    public Kommentar getKommentar() {
        return kommentar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bewertung bewertung = (Bewertung) o;
        return filiale.equals(bewertung.filiale) && datum.equals(bewertung.datum) && wertung.equals(bewertung.wertung) && Objects.equals(kommentar, bewertung.kommentar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filiale, datum, wertung, kommentar);
    }

    @Override
    public String toString() {
        return "Bewertung{" +
                "filiale=" + filiale +
                ", datum=" + datum +
                ", wertung=" + wertung +
                ", kommentar=" + kommentar +
                '}';
    }

    public static Builder Builder() { return  new Builder(); }

    private static class Builder {
        private Filiale filiale;
        private BewertungsDatum datum;
        private Wertung wertung;
        private Kommentar kommentar;

        private Builder() {}

        public Builder setFiliale(@NotNull Filiale filiale) {
            this.filiale = filiale;

            return this;
        }

        public Builder setBewertungsDatum(@NotNull Date datum) {
            this.datum = new BewertungsDatum(datum);

            return this;
        }

        public Builder setWertung(@NotNull int wertung) {
            this.wertung = new Wertung(wertung);

            return this;
        }

        public Builder setKommentar(String kommentar) {
            this.kommentar = new Kommentar(kommentar);

            return this;
        }

        /**
         * baut eine Instanz von Bewertung, wenn die Felder {@link #filiale}, {@link #datum} und {@link #wertung} befüllt sind
         *
         * @return Bewertung
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public Bewertung build() {

            if (this.filiale == null) {
                throw new ValidationException("Bewertung.Builder: Filiale darf nicht null sein!");
            }
            if (this.datum == null) {
                throw new ValidationException("Berwertung.Builder: Datum darf nicht null sein!");
            }
            if (this.wertung == null) {
                throw new ValidationException("Bewertung.Builder: Wertung darf nicht null sein!");
            }

            return new Bewertung(this);
        }
    }
}
