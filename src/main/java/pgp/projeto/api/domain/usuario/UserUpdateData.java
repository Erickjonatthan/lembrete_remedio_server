package pgp.projeto.api.domain.usuario;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;



public record UserUpdateData(@NotNull UUID id, String nome, String email, String senha, LocalDate dataNascimento, byte[] fotoPerfil) {
    
}
