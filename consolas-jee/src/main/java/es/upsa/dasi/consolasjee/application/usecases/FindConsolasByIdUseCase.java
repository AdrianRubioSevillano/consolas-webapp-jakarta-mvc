package es.upsa.dasi.consolasjee.application.usecases;

import es.upsa.dasi.consolasjee.domain.model.Consola;

import java.util.Optional;

public interface FindConsolasByIdUseCase {
    Optional<Consola> findConsolaById(long id);
}
