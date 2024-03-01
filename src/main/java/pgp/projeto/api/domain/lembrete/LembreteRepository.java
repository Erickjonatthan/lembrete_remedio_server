package pgp.projeto.api.domain.lembrete;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LembreteRepository extends JpaRepository<Lembrete, UUID> {


    @Query("SELECT l FROM Lembrete l WHERE l.usuario.id = :userId")
    Page<Lembrete> findByUsuario(Pageable paginacao, UUID userId);
}
