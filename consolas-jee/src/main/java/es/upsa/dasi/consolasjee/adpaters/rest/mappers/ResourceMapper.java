package es.upsa.dasi.consolasjee.adpaters.rest.mappers;


import adapters.rest.dtos.ConsolaFullResponse;
import adapters.rest.dtos.ConsolaResponse;
import es.upsa.dasi.consolasjee.adpaters.rest.dtos.ConsolaPostRequest;
import es.upsa.dasi.consolasjee.adpaters.rest.dtos.ConsolaPutRequest;
import es.upsa.dasi.consolasjee.domain.model.AddConsolaCommand;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.model.ReplaceConsolaCommand;
import jakarta.ws.rs.core.UriInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.net.URI;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ResourceMapper {
    ConsolaResponse toConsolaResponse(Consola consola);
    @Mapping(target = "uri", expression = "java(createUri(consola, uriInfo))")
    ConsolaFullResponse toConsolaFullResponse(Consola consola, UriInfo uriInfo);
    AddConsolaCommand toAddConsolaCommand(ConsolaPostRequest consolaPostRequest);
    ReplaceConsolaCommand toReplaceConsolaCommand(ConsolaPutRequest consolaPutRequest);

    default URI createUri(Consola consola, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path("/consolas/{id}")
                .resolveTemplate("id", consola.getId())
                .build();
    }
}
