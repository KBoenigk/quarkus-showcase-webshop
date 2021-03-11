package de.openknowledge.projects.webshop.domain.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.lieferung.LieferAdresse;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Bestellung {

    @NotNull
    private BestellungsID id;

    @NotNull
    private ProduktListe produkte;

    @NotNull
    private LieferAdresse lieferAdresse;

   //@NotNull
    private RechnungsAdresse rechnungsAdresse;

    public Bestellung(Builder b) {
        this.id = new BestellungsID();
        this.produkte = b.produkte;
        this.lieferAdresse = b.lieferAdresse;
        this.rechnungsAdresse = b.rechnungsAdresse;
    }

    public BestellungsID getId() {
        return id;
    }

    public ProduktListe getProdukte() {
        return produkte;
    }

    public LieferAdresse getLieferAdresse() {
        return lieferAdresse;
    }

    public RechnungsAdresse getRechnungsAdresse() {
        return rechnungsAdresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellung that = (Bestellung) o;
        return id.equals(that.id) && produkte.equals(that.produkte) && lieferAdresse.equals(that.lieferAdresse) && Objects.equals(rechnungsAdresse, that.rechnungsAdresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produkte, lieferAdresse, rechnungsAdresse);
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", produkte=" + produkte +
                ", lieferAdresse=" + lieferAdresse +
                ", rechnungsAdresse=" + rechnungsAdresse +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private ProduktListe produkte;
        private LieferAdresse lieferAdresse;
        private RechnungsAdresse rechnungsAdresse;

        private Builder() {}

        public Builder setProduktListe(@NotNull ProduktListe produkte) {
            this.produkte = produkte;

            return this;
        }

        public Builder setLieferAdresse(@NotNull LieferAdresse lieferAdresse) {
            this.lieferAdresse = lieferAdresse;

            return this;
        }

        public Builder setRechnungsAdresse(@NotNull RechnungsAdresse rechnungsAdresse) {
            this.rechnungsAdresse = rechnungsAdresse;

            return this;
        }

        /**
         * baut eine Instanz von Bestellung, wenn die Felder {@link #produkte}, {@link #lieferAdresse} und
         * {@link #rechnungsAdresse} befüllt sind
         *
         * @return Bestellung
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public Bestellung build() {
            if (this.produkte == null) {
                throw new ValidationException("Bestellung.Builder: Produkte darf nicht null sein!");
            }
            if (this.lieferAdresse == null) {
                throw new ValidationException("Bestellung.Builder: Lieferadresse darf nicht null sein!");
            }
//            if (this.rechnungsAdresse == null) {
//                throw new ValidationException("Bestellung.Builder: Rechnungsadresse darf nicht null sein!");
//            }

            return new Bestellung(this);
        }
    }
}
