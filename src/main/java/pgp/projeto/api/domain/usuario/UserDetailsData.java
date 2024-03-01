package pgp.projeto.api.domain.usuario;

import java.time.LocalDate;
import java.util.UUID;

public record UserDetailsData(UUID id, String nome, String email, LocalDate dataNascimento, byte[] fotoPerfil) {
    public UserDetailsData(UserAccount user) {
        this(user.getId(), user.getNome(), user.getLogin(), user.getDataNascimento(), user.getPerfilFoto());
    }
}
