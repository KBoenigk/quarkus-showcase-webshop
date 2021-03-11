package de.openknowledge.projects.webshop.application.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.Bestellung;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("bestellung")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BestellResource {
    private static final Logger LOG = LoggerFactory.getLogger(BestellResource.class);

    @Inject
    private BestellApplicationService bestellApplicationService;

    @GET
    @Operation(operationId = "getBestellungen", description = "Get all Bestellungen")
    @APIResponse(responseCode = "200", description = "Ok")
    public Response getBestellungen() {
        LOG.info("Get Bestellungen");

        List<Bestellung> bestellungen = this.bestellApplicationService.getBestellungen();

        LOG.info("{}", bestellungen);

        return Response.status(Response.Status.OK)
                .entity(bestellungen)
                .build();
    }

    @PUT
    @Operation(operationId = "placeBestellung", description = "Bestellung abgeschickt")
    @RequestBody(name = "bestellung", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = BestellungDTO.class, type = SchemaType.OBJECT)))
    @APIResponse(responseCode = "201", description = "Bestellung angenommen")
    @APIResponse(responseCode = "400", description = "Bestellung abgelehnt")
    public Response placeBestellung(@NotNull @Valid final BestellungDTO bestellung) {
        LOG.info("Add new Bestellung {}", bestellung);

        ZahlungsAufforderungDTO aufforderung = bestellApplicationService.placeBestellung(bestellung);

        LOG.info("Bestellung placed");

        return Response.status(Response.Status.CREATED).entity(aufforderung).build();
    }
}
