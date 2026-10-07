package es.upsa.dasi.consolasjee.infrastructure.persistance.dao;

import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao {
    List<ConsolaRow> findAllConsolas();
    Optional<ConsolaRow> findConsolaById(long id);
    ConsolaRow insertConsola(ConsolaRow consolaRow);
    Optional<ConsolaRow> updateConsola(ConsolaRow consolaRow);
    void deleteConsolaById(long id);
}
