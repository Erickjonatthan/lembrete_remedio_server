package pgp.projeto.api.domain.lembrete;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record DadosRegistroLembrete(
        
        @NotNull UUID idUsuario,
        @NotNull LocalDate dataInicio,
        @NotNull String nomeMedicamento,
        @NotNull LocalTime horario,
        @NotNull Float dosagem,
        @NotNull int intervaloHora
) {
}
