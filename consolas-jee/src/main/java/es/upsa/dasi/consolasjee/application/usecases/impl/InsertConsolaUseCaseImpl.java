package es.upsa.dasi.consolasjee.application.usecases.impl;

import es.upsa.dasi.consolasjee.application.usecases.InsertConsolaUseCase;
import es.upsa.dasi.consolasjee.application.usecases.mappers.UseCaseMapper;
import es.upsa.dasi.consolasjee.domain.model.AddConsolaCommand;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class InsertConsolaUseCaseImpl implements InsertConsolaUseCase {

    Repository repository;
    UseCaseMapper mapper;

    @Inject
    public InsertConsolaUseCaseImpl(Repository repository, UseCaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Consola execute(AddConsolaCommand command) {
        Consola consola = mapper.toConsola(command);
        return repository.insertConsola(consola);
    }
}
