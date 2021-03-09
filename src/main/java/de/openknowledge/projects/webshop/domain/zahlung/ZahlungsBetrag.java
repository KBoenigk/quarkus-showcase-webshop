package de.openknowledge.projects.webshop.domain.zahlung;

import java.math.BigDecimal;

public class ZahlungsBetrag {
    private BigDecimal betrag;

    public ZahlungsBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }
}
