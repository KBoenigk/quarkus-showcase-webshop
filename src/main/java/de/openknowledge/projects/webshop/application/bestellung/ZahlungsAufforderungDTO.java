package de.openknowledge.projects.webshop.application.bestellung;

import org.apache.commons.lang3.Validate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Ein DTO, das eine Zahlungsaufforderung repräsentiert.
 */
public class ZahlungsAufforderungDTO {

    @NotNull
    private String bestellID;

    @NotNull
    private String zahlungsID;

    @Min(0)
    private double betrag;

    protected ZahlungsAufforderungDTO() {
        super();
    }

    public ZahlungsAufforderungDTO(final String bestellID, final String zahlungsID, final double betrag) {
        this();
        this.bestellID = Validate.notNull(bestellID, "BestellID darf nicht null sein.");
        this.zahlungsID = Validate.notNull(zahlungsID, "ZahlungsID darf nicht null sein.");
        // Validation > 0
        this.betrag = betrag;
    }

    public String getBestellID() {
        return bestellID;
    }

    public void setBestellID(String bestellID) {
        this.bestellID = bestellID;
    }

    public String getZahlungsID() {
        return zahlungsID;
    }

    public void setZahlungsID(String zahlungsID) {
        this.zahlungsID = zahlungsID;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsAufforderungDTO that = (ZahlungsAufforderungDTO) o;
        return Double.compare(that.betrag, betrag) == 0 && bestellID.equals(that.bestellID) && zahlungsID.equals(that.zahlungsID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bestellID, zahlungsID, betrag);
    }

    @Override
    public String toString() {
        return "ZahlungsAufforderungDTO{" +
                "bestellID='" + bestellID + '\'' +
                ", zahlungsID='" + zahlungsID + '\'' +
                ", betrag=" + betrag +
                '}';
    }
}
