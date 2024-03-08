package pgp.projeto.api.domain.usuario.authentication;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import pgp.projeto.api.domain.usuario.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, UUID> {

    
    UserAccount findByLogin(String login);

    boolean existsByLogin(@NotBlank @Email String email);

    
}
