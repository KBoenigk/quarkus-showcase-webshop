package de.openknowledge.projects.webshop.domain.bestellung;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUKT")
public class Produkt extends PanacheEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(name = "PREIS", nullable = false)
    @NotNull
    private BigDecimal preis;

    @Column(name = "BESCHREIBUNG")
    private String beschreibung;

    protected Produkt() { super(); }

    public Produkt(Builder b) {
        this();
        this.name = b.name;
        this.preis = b.preis;
        this.beschreibung = b.beschreibung;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public String getBeschreibung() {
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
        private String name;
        private BigDecimal preis;
        private String beschreibung;

        private Builder () {}

        public Builder setProduktName(@NotNull String name) {
            this.name = name;

            return this;
        }

        public Builder setProduktPreis(@NotNull BigDecimal preis) {
            this.preis = preis;

            return this;
        }

        public Builder setProduktBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;

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
