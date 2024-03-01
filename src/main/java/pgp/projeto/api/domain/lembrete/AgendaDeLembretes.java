package pgp.projeto.api.domain.lembrete;


import pgp.projeto.api.domain.usuario.authentication.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class AgendaDeLembretes {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Autowired
    private UserRepository userRepository;

    public DadosDetalhamentoLembrete agendar(DadosRegistroLembrete dados) {

        var lembrete = new Lembrete(
            null,
            userRepository.getReferenceById(dados.idUsuario()),
            dados.dataInicio(),
            dados.nomeMedicamento(),
            dados.horario(),
            dados.dosagem(),
            dados.intervaloHora()
        );
        lembreteRepository.save(lembrete);

        return new DadosDetalhamentoLembrete(lembrete);
    }

    public void excluir(DadosCancelamentoLembrete dados) {
       
        var lembrete = lembreteRepository.getReferenceById(dados.idLembrete());
        lembreteRepository.delete(lembrete);

        
    }

    public DadosDetalhamentoLembrete atualizar(@Valid DadosAtualizarLembrete dados) {
        // Buscar o lembrete existente
        var lembrete = lembreteRepository.getReferenceById(dados.id());

        // Atualizar as propriedades do lembrete
        lembrete.setNomeMedicamento(dados.nomeMedicamento());
        lembrete.setDataInicio(dados.dataInicio());
        lembrete.setHorario(dados.horario());
        lembrete.setDosagem(dados.dosagem());
        lembrete.setIntervaloHora(dados.intervaloHora());
        
       
        lembreteRepository.save(lembrete);

        // Retornar os detalhes do lembrete atualizado
        return new DadosDetalhamentoLembrete(lembrete);
    }


}
