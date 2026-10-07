package es.upsa.dasi.consolasjee.application.usecases.impl;

import es.upsa.dasi.consolasjee.application.usecases.FindConsolasByIdUseCase;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;


@ApplicationScoped
public class FindConsolasByIdUseCaseImpl implements FindConsolasByIdUseCase {

    @Inject
    Repository repository;

    @Override
    public Optional<Consola> findConsolaById(long id) {
        return repository.findConsolaById(id);
    }
}
