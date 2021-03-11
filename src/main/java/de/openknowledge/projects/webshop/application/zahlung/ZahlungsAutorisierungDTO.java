package de.openknowledge.projects.webshop.application.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.ZahlungsAutorisierung;
import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ZahlungsAutorisierungDTO {

    @NotNull
    private String zahlungsID;

    protected ZahlungsAutorisierungDTO() { super(); }

    public ZahlungsAutorisierungDTO(final String zahlungsID) {
        this.zahlungsID = Validate.notNull(zahlungsID, "Zahlungs ID darf nicht null sein.");
    }

    public String getZahlungsID() {
        return zahlungsID;
    }

    public void setZahlungsID(String zahlungsID) {
        this.zahlungsID = zahlungsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsAutorisierungDTO that = (ZahlungsAutorisierungDTO) o;
        return zahlungsID.equals(that.zahlungsID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zahlungsID);
    }

    @Override
    public String toString() {
        return "ZahlungsAutorisierungDTO{" +
                "zahlungsID='" + zahlungsID + '\'' +
                '}';
    }
}
