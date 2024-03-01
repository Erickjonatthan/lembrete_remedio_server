package pgp.projeto.api.domain.usuario.authentication;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import pgp.projeto.api.domain.usuario.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, UUID> {

    
    UserAccount findByLogin(String login);

    
}
