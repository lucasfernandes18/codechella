package br.com.alura.codechella;

import java.time.LocalDate;

public record EventoDTO(Long id, TipoEvento tipoEvento, String nome, LocalDate data, String descricao) {


public static EventoDTO toDTO(EventoEntity eventoDTO) {
    return new EventoDTO(eventoDTO.getId(), eventoDTO.getTipo(), eventoDTO.getNome(), eventoDTO.getData(), eventoDTO.getDescricao() );
}

}
