package de.openknowledge.projects.webshop.application.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Eine Resource, die Zugriff auf Zahlungen bereitstellt.
 */
@Path("zahlung")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ZahlungsResource {

    private static final Logger LOG = LoggerFactory.getLogger(ZahlungsResource.class);

    @Inject
    private ZahlungsApplicationService zahlungsService;

    @GET
    @Operation(operationId = "getZahlungen", description = "Get all Zahlungen")
    @APIResponse(responseCode = "200", description = "Ok")
    public Response getZahlungen() {
        LOG.info("Getting Zahlungen");

        List<Zahlung> zahlungen = this.zahlungsService.getZahlungen();

        LOG.info("{} Zahlungen found.", zahlungen.size());

        return Response.status(Response.Status.OK)
                .entity(zahlungen)
                .build();
    }

    @PUT
    @Path("{zahlungsID}/autorisiere")
    @Operation(operationId = "autorisiereZahlung", description = "Zahlung autorisiert")
    @APIResponse(responseCode = "201", description = "Autorisierung erfolgreich, Bestellung bestätigt")
    @APIResponse(responseCode = "400", description = "Autorisierung fehlgeschlagen")
    public Response autorisiereZahlung(@PathParam("zahlungsID") final String zahlungsID) {
        LOG.info("Autorisiere Zahlung");

        this.zahlungsService.autorisiereZahlung(zahlungsID);

        LOG.info("Zahlung erfolgreich autorisiert");

        return Response.status(Response.Status.CREATED).build();
    }
}
