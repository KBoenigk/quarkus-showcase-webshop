package de.openknowledge.projects.webshop.application;

import de.openknowledge.projects.webshop.domain.HelloWorldObject;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("produkt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProduktResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProduktResource.class);

    @Inject
    private ProduktRepository repository;

    @GET
    public Response getProduktListe() {

        List<Produkt> produktListe = repository.read();

        return Response.status(Response.Status.OK)
                .entity(produktListe)
                .build();
    }
}
