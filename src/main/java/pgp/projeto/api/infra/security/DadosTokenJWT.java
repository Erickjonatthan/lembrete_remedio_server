package pgp.projeto.api.infra.security;

import java.util.UUID;

public record DadosTokenJWT(String token, UUID contaId) {

}
