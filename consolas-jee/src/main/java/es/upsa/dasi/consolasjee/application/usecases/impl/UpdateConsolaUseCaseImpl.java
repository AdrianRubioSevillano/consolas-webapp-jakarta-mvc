package es.upsa.dasi.consolasjee.application.usecases.impl;

import es.upsa.dasi.consolasjee.application.usecases.UpdateConsolaUseCase;
import es.upsa.dasi.consolasjee.application.usecases.mappers.UseCaseMapper;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.model.ReplaceConsolaCommand;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class UpdateConsolaUseCaseImpl implements UpdateConsolaUseCase {

    Repository repository;
    UseCaseMapper mapper;


    @Inject
    public UpdateConsolaUseCaseImpl(Repository repository, UseCaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Consola updateConsola(ReplaceConsolaCommand command, long id) {
        Consola consola = mapper.toConsola(command, id);
        return repository.updateConsola(consola);
    }
}
