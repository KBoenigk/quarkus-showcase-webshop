package de.openknowledge.projects.webshop.domain.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProduktListenElement {

    @NotNull
    private Produkt produkt;

    @NotNull
    private ProduktMenge menge;

    public ProduktListenElement(Builder b) {
        this.produkt = b.produkt;
        this.menge = b.menge;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public ProduktMenge getMenge() {
        return menge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktListenElement that = (ProduktListenElement) o;
        return produkt.equals(that.produkt) && menge.equals(that.menge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produkt, menge);
    }

    @Override
    public String toString() {
        return "ProduktListenElement{" +
                "produkt=" + produkt +
                ", menge=" + menge +
                '}';
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Builder {
        private Produkt produkt;
        private ProduktMenge menge;

        private Builder() {}

        public Builder setProdukt(@NotNull Produkt produkt) {
            this.produkt = produkt;

            return this;
        }

        public Builder setProduktMenge(@NotNull int menge) {
            this.menge = new ProduktMenge(menge);

            return this;
        }

        /**
         * baut eine Instanz von ProduktListenElement, wenn die Felder {@link #produkt} und {@link #menge} befüllt sind
         *
         * @return ProduktListenElement
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public ProduktListenElement build() {
            if (this.produkt == null) {
                throw new ValidationException("Filiale.Builder: Name darf nicht null sein!");
            }
            if (this.menge == null) {
                throw new ValidationException("Filiale.Builder: Adresse darf nicht null sein!");
            }

            return new ProduktListenElement(this);
        }
    }
}
