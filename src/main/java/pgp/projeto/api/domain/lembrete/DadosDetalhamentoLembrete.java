package pgp.projeto.api.domain.lembrete;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record DadosDetalhamentoLembrete(UUID id, UUID idUsuario, LocalDate dataInicio, String nomeMedicamento, LocalTime horario, Float dosagem, int intervaloHora) {
    public DadosDetalhamentoLembrete(Lembrete lembrete) {
        this(lembrete.getId(), lembrete.getUsuario().getId(), lembrete.getDataInicio(), lembrete.getNomeMedicamento(), lembrete.getHorario(), lembrete.getDosagem(), lembrete.getIntervaloHora());
    }
}
