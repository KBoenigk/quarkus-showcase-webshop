package de.openknowledge.projects.webshop.domain.bestellung.lieferung;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class LieferEnde {
    private Date value;

    public LieferEnde(@NotNull Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }
}
