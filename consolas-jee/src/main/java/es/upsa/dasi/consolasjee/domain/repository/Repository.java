package es.upsa.dasi.consolasjee.domain.repository;

import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Consola> findAllConsolas();
    Optional<Consola> findConsolaById(long id);
    Consola insertConsola(Consola consola);
    Consola updateConsola(Consola consola);
    void deleteConsolaById(long id);
}
