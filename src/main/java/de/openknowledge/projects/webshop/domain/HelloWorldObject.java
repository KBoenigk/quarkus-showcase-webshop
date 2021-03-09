package de.openknowledge.projects.webshop.domain;

public class HelloWorldObject {

    private HelloWorldMessage message;

    public HelloWorldObject(HelloWorldMessage message) {
        this.message = message;
    }

    public HelloWorldMessage getMessage() {
        return message;
    }
}
