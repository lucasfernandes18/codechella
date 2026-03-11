package br.com.alura.codechella;

import java.time.LocalDate;

public record EventoDTO(Long id, TipoEvento tipoEvento, String nome, LocalDate data, String descricao) {


public static EventoDTO toDTO(EventoEntity eventoDTO) {
    return new EventoDTO(eventoDTO.getId(), eventoDTO.getTipo(), eventoDTO.getNome(), eventoDTO.getData(), eventoDTO.getDescricao() );
}
public EventoEntity toEntity() {
    EventoEntity evento = new EventoEntity();
    evento.setId(this.id);
    evento.setTipo(this.tipoEvento);
    evento.setNome(this.nome);
    evento.setData(this.data);
    evento.setDescricao(this.descricao);
    return evento;

}

}
