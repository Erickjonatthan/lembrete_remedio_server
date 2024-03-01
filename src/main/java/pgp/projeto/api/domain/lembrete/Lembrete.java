package pgp.projeto.api.domain.lembrete;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pgp.projeto.api.domain.usuario.UserAccount;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table(name = "lembretes")
@Entity(name = "Lembrete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private UserAccount usuario;

    private LocalDate dataInicio;
    private String nomeMedicamento;
    private LocalTime horario;
    private Float dosagem;
    private int intervaloHora;
  
 

}
