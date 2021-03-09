package de.openknowledge.projects.webshop.domain.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Produkt {

    @NotNull
    private ProduktName name;

    @NotNull
    private ProduktPreis preis;

    private ProduktBeschreibung beschreibung;

    public Produkt(Builder b) {
        this.name = b.name;
        this.preis = b.preis;
        this.beschreibung = b.beschreibung;
    }

    public ProduktName getName() {
        return name;
    }

    public ProduktPreis getPreis() {
        return preis;
    }

    public ProduktBeschreibung getBeschreibung() {
        return beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkt produkt = (Produkt) o;
        return name.equals(produkt.name) && preis.equals(produkt.preis) && Objects.equals(beschreibung, produkt.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, preis, beschreibung);
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "name=" + name +
                ", preis=" + preis +
                ", beschreibung=" + beschreibung +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private ProduktName name;
        private ProduktPreis preis;
        private ProduktBeschreibung beschreibung;

        private Builder () {}

        public Builder setProduktName(@NotNull String name) {
            this.name = new ProduktName(name);

            return this;
        }

        public Builder setProduktPreis(@NotNull BigDecimal preis) {
            this.preis = new ProduktPreis(preis);

            return this;
        }

        public Builder setProduktBeschreibung(String beschreibung) {
            this.beschreibung = new ProduktBeschreibung(beschreibung);

            return this;
        }

        /**
         * baut eine Instanz von Produkt, wenn die Felder {@link #name} und {@link #preis} befüllt sind
         *
         * @return Produkt
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public Produkt build() {
            if (this.name == null) {
                throw new ValidationException("Produkt.Builder: Name darf nicht null sein!");
            }
            if (this.preis == null) {
                throw new ValidationException("Produkt.Builder: Preis darf nicht null sein!");
            }

            return new Produkt(this);
        }
    }
}
