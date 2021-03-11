package de.openknowledge.projects.webshop.domain.zahlung;

import java.math.BigDecimal;
import java.util.Objects;

public class ZahlungsBetrag {
    private BigDecimal betrag;

    public ZahlungsBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsBetrag that = (ZahlungsBetrag) o;
        return betrag.equals(that.betrag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betrag);
    }
}
