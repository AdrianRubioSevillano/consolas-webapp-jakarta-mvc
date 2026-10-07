package es.upsa.dasi.consolasjee.infrastructure.persistance.dao.mappers;


import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface DaoMapper {
    ConsolaRow toConsolaRow(Consola consola);
    Consola toConsola(ConsolaRow consolaRow);
}
