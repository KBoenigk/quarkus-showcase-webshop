package de.openknowledge.projects.webshop.application.zahlung;

import de.openknowledge.projects.webshop.domain.zahlung.Zahlung;
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
    @Operation(operationId = "autorisiereZahlung", description = "Zahlung autorisiert")
    @RequestBody(name = "zahlungsAutorisierung", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = ZahlungsAutorisierungDTO.class, type = SchemaType.OBJECT)))
    @APIResponse(responseCode = "200", description = "Autorisierung erfolgreich, Bestellung bestätigt")
    @APIResponse(responseCode = "400", description = "Autorisierung fehlgeschlagen")
    public Response autorisiereZahlung(@NotNull @Valid final ZahlungsAutorisierungDTO zahlungsAutorisierung) {
        LOG.info("Autorisiere Zahlung");

        this.zahlungsService.autorisiereZahlung(zahlungsAutorisierung);

        LOG.info("Zahlung erfolgreich autorisiert");

        return Response.status(Response.Status.OK).build();
    }
}
