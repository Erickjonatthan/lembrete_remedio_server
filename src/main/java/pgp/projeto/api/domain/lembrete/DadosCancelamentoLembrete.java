package pgp.projeto.api.domain.lembrete;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoLembrete(
        @NotNull
        UUID idLembrete
       ) {
}
