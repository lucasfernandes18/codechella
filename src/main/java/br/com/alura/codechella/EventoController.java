package br.com.alura.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.awt.*;
import java.time.Duration;

@RestController
@RequestMapping("/eventos")
public class EventoController {


    private final EventoService eventoService;

    private final Sinks.Many<EventoDTO> eventoSink;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
        //milticast faz o envio das informaçãoes para todos os clientes
        //backpressure controla fluxo de dados entre o servidor e o cliente. Previne que o cliente não receba um fluxo que não consiga consumir
        this.eventoSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping(value = "/categoria/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<EventoDTO> listarPorTipo(@PathVariable String tipo) {
        return Flux.merge(eventoService.obterPorTipo(tipo), eventoSink.asFlux())
                .delayElements(Duration.ofSeconds(4));
    }
    @GetMapping
    public Flux<EventoDTO> obterTodos() {
        return eventoService.obterTodos();
    }

    @GetMapping("/obterPorId/{id}")
    public Mono<EventoDTO> obterPorId(@PathVariable Long id) {
        return eventoService.obterPorId(id);
    }

    @PostMapping
    public Mono<EventoDTO> cadastrar(@RequestBody EventoDTO eventoDTO) {
        return eventoService.cadastrar(eventoDTO)
                .doOnSuccess(e -> eventoSink.tryEmitNext(e));
    }

    @PutMapping("/atualizar/{id}")
    public Mono<EventoDTO> atualizar(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        return eventoService.atualizar(id, eventoDTO);
    }


    @DeleteMapping("/deletar/{id}")
    public Mono<Void> excluir(@PathVariable Long id) {
        return eventoService.excluir(id);
    }




}
