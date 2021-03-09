package de.openknowledge.projects.webshop.domain.zahlung;

public class ZahlungsAbschluss {
    private boolean value;

    public ZahlungsAbschluss(boolean value) {
        this.value = value;
    }

    public boolean isAbgeschlossen() {
        return value;
    }
}
