package de.openknowledge.projects.webshop.domain.bestellung;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Postleitzahl {

    private String value;

    public Postleitzahl(@NotNull @Pattern(regexp="^[0-9]{5}$") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
