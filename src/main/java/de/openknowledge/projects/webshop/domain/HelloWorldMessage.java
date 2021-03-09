package de.openknowledge.projects.webshop.domain;

import javax.validation.constraints.NotNull;

public class HelloWorldMessage {
    private String value;

    public HelloWorldMessage(@NotNull String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
