package br.com.alura.codechella;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventoRepository extends ReactiveCrudRepository<EventoEntity, Long> {
    Flux<EventoEntity> findByTipo(TipoEvento tipoEvento);
}
