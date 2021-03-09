package de.openknowledge.projects.webshop.domain.zahlung;

public class ZahlungsAutorisierung {
    private boolean value;

    public ZahlungsAutorisierung(boolean value) {
        this.value = value;
    }

    public boolean isAutorisiert() {
        return value;
    }
}
