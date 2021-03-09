package de.openknowledge.projects.webshop.domain.bewertung;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class BewertungsDatum {
    private Date value;

    public BewertungsDatum(@NotNull @Past Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }
}
