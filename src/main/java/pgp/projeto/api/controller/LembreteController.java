package pgp.projeto.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import pgp.projeto.api.domain.lembrete.AgendaDeLembretes;
import pgp.projeto.api.domain.lembrete.DadosAtualizarLembrete;
import pgp.projeto.api.domain.lembrete.DadosCancelamentoLembrete;
import pgp.projeto.api.domain.lembrete.DadosDetalhamentoLembrete;
import pgp.projeto.api.domain.lembrete.DadosRegistroLembrete;
import pgp.projeto.api.domain.lembrete.LembreteRepository;
import pgp.projeto.api.domain.usuario.UserAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lembretes")
@SecurityRequirement(name = "bearer-key")
public class LembreteController {

    @Autowired
    private AgendaDeLembretes lembrete;

    @Autowired
    private LembreteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid DadosRegistroLembrete dados) {
        var dto = lembrete.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity excluir(@RequestBody @Valid DadosCancelamentoLembrete dados) {
        lembrete.excluir(dados);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoLembrete>> listar(@PageableDefault(size = 10, sort = {"nomeMedicamento"}) Pageable paginacao, Authentication authentication) {
       
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        UUID userId =  userAccount.getId();
    
        // Buscar apenas os lembretes para o usuário com o ID extraído do token
        var page = repository.findByUsuario(paginacao, userId).map(DadosDetalhamentoLembrete::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarLembrete dados) {
        var dto = lembrete.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

}
