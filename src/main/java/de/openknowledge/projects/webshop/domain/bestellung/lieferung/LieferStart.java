package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class LieferStart {
    private Date value;

    public LieferStart(@NotNull Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }
}
