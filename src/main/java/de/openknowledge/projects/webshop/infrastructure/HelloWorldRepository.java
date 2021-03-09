package de.openknowledge.projects.webshop.infrastructure;

import de.openknowledge.projects.webshop.domain.HelloWorldMessage;
import de.openknowledge.projects.webshop.domain.HelloWorldObject;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloWorldRepository {
    public HelloWorldObject read() {
        HelloWorldObject object = new HelloWorldObject(new HelloWorldMessage("Hello Wolrd!"));
        return object;
    }
}
