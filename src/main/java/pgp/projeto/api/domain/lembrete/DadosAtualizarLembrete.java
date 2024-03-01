package pgp.projeto.api.domain.lembrete;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarLembrete(@NotNull UUID id, String nomeMedicamento, LocalDate dataInicio, LocalTime horario ,  Float dosagem, int intervaloHora) {

}
