package br.com.alura.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDTO> obterTodos() {
        return eventoService.obterTodos();
    }

    @GetMapping("/obterPorId/{id}")
    public Mono<EventoDTO> obterPorId(@PathVariable Long id) {
        return eventoService.obterPorId(id);
    }

    @PostMapping
    public Mono<EventoDTO> cadastrar(@RequestBody EventoDTO eventoDTO) {
        return eventoService.cadastrar(eventoDTO);
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
