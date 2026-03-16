package br.com.alura.codechella;

import java.time.LocalDate;

public record EventoDTO(Long id, TipoEvento tipo, String nome, LocalDate data, String descricao) {


public static EventoDTO toDTO(EventoEntity eventoEntity) {
    return new EventoDTO(eventoEntity.getId(), eventoEntity.getTipo(), eventoEntity.getNome(), eventoEntity.getData(), eventoEntity.getDescricao() );
}
public EventoEntity toEntity() {
    EventoEntity evento = new EventoEntity();
    evento.setId(this.id);
    evento.setTipo(this.tipo);
    evento.setNome(this.nome);
    evento.setData(this.data);
    evento.setDescricao(this.descricao);
    return evento;

}

}
