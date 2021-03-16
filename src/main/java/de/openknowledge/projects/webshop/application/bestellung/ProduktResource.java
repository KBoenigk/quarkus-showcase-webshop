package de.openknowledge.projects.webshop.application.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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

/**
 * Eine Resource, die Zugriff auf Produkte bereitstellt.
 */
@Path("produkt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProduktResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProduktResource.class);

    @Inject
    private ProduktRepository repository;

    @GET
    @Operation(operationId = "getProdukte", description = "Get all Produkte")
    @APIResponse(responseCode = "200", description = "Ok")
    public Response getProduktListe() {

        LOG.info("Getting Produkte");

        List<Produkt> produktListe = repository.read();

        LOG.info("{} Produkte found", produktListe.size());

        return Response.status(Response.Status.OK)
                .entity(produktListe)
                .build();
    }
}
