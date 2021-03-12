package de.openknowledge.projects.webshop.domain.zahlung;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Zahlung {

    @NotNull
    private ZahlungsID id;

    @NotNull
    private Bestellung bestellung;

    //@NotNull
    private ZahlungsArt zahlungsArt;

    @NotNull
    private ZahlungsBetrag betrag;

    private ZahlungsAutorisierung autorisierung;

    private ZahlungsAbschluss abschluss;

    private Zahlung(Builder b) {
        this.id = new ZahlungsID();
        this.bestellung = b.bestellung;
        this.zahlungsArt = b.zahlungsArt;
        this.betrag = b.betrag;
        this.autorisierung = b.autorisierung;
        this.abschluss = b.abschluss;
    }
    public ZahlungsID getZahlungsId() {
        return id;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public ZahlungsArt getZahlungsArt() {
        return zahlungsArt;
    }

    public ZahlungsBetrag getBetrag() {
        return betrag;
    }

    public ZahlungsAutorisierung getAutorisierung() {
        return autorisierung;
    }

    public ZahlungsAbschluss getAbschluss() {
        return abschluss;
    }

    public void setAutorisierung(@NotNull ZahlungsAutorisierung autorisierung) {
        this.autorisierung = autorisierung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zahlung zahlung = (Zahlung) o;
        return id.equals(zahlung.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bestellung, zahlungsArt, betrag, autorisierung, abschluss);
    }

    @Override
    public String toString() {
        return "Zahlung{" +
                "id=" + id +
                ", bestellung=" + bestellung +
                ", zahlungsArt=" + zahlungsArt +
                ", betrag=" + betrag +
                ", autorisierung=" + autorisierung +
                ", abschluss=" + abschluss +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private Bestellung bestellung;
        private ZahlungsArt zahlungsArt;
        private ZahlungsBetrag betrag;
        private ZahlungsAutorisierung autorisierung;
        private ZahlungsAbschluss abschluss;

        private Builder() {}

        public Builder setBestellung(@NotNull Bestellung bestellung) {
            this.bestellung = bestellung;

            return this;
        }

        public Builder setZahlungsart(@NotNull ZahlungsArt zahlungsArt) {
            this.zahlungsArt = zahlungsArt;

            return this;
        }

//        public Builder setZahlungsBetrag(@NotNull ZahlungsBetrag betrag) {
//            this.betrag = betrag;
//
//            return this;
//        }

        public Builder setAutorisierung(@NotNull ZahlungsAutorisierung autorisierung) {
            this.autorisierung = autorisierung;

            return this;
        }

        public Builder setAbschluss(@NotNull ZahlungsAbschluss abschluss) {
            this.abschluss = abschluss;

            return this;
        }

        /**
         * baut eine Instanz von Zahlung, wenn die Felder {@link #bestellung}, {@link #zahlungsArt} und
         * {@link #betrag} befüllt sind
         *
         * @return Zahlung
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public Zahlung build() {

            if (this.bestellung == null) {
                throw new ValidationException("Zahlung.Builder: Bestellung darf nicht null sein!");
            }
//            if (this.zahlungsArt == null) {
//                throw new ValidationException("Zahlung.Builder: Zahlungsart darf nicht null sein!");
//            }
//            if (this.betrag == null) {
//                throw new ValidationException("Zahlung.Builder: Betrag darf nicht null sein!");
//            }
            BigDecimal betrag = this.bestellung.getProdukte().getProduktListe().stream().map((produkt) -> {
                return produkt.getProdukt().getPreis().multiply(BigDecimal.valueOf(produkt.getMenge().getValue()));
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            this.betrag = new ZahlungsBetrag(betrag);
            return new Zahlung(this);
        }
    }
}
