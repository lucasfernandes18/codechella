package br.com.alura.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Flux<EventoDTO> obterTodos() {
        return eventoRepository.findAll()
                .map(EventoDTO::toDTO);
    }

    public Mono<EventoDTO> obterPorId(Long id) {
        return eventoRepository.findById(id)
                //se recebido um id que não existe lançará a exceção.
                .switchIfEmpty( Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(EventoDTO::toDTO);
    }


    public Mono<EventoDTO> cadastrar(EventoDTO eventoDTO) {
        return eventoRepository.save(eventoDTO.toEntity())
                .map(EventoDTO::toDTO);
    }


    public Mono<Void> excluir(@PathVariable Long id) {
        return eventoRepository.deleteById(id);

    }


    public Mono<EventoDTO> atualizar(@PathVariable Long id, EventoDTO eventoDTO) {
        return eventoRepository.findById(id)
                .flatMap(evento -> {
                    evento.setTipo(eventoDTO.tipo());
                    evento.setNome(eventoDTO.nome());
                    evento.setData(eventoDTO.data());
                    evento.setDescricao(eventoDTO.descricao());

                    return eventoRepository.save(evento);
                })
                .map(EventoDTO::toDTO);
    }
}
