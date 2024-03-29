package pgp.projeto.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pgp.projeto.api.domain.usuario.UserAccount;
import pgp.projeto.api.domain.usuario.UserDetailsData;
import pgp.projeto.api.domain.usuario.UserRegistrationData;
import pgp.projeto.api.domain.usuario.UserUpdateData;
import pgp.projeto.api.domain.usuario.authentication.UserRepository;
import pgp.projeto.api.domain.usuario.email.EmailService;



@RestController
@RequestMapping("/cadastro")
public class UserAccountController {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UserRegistrationData dados, UriComponentsBuilder uriBuilder) {
        
        if(repository.existsByLogin(dados.email())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");
        }

        var usuario = new UserAccount(dados,passwordEncoder);
        repository.save(usuario);
        var uri = uriBuilder.path("/cadastro/{id}").buildAndExpand(usuario.getId()).toUri();


        emailService.sendWelcomeEmail(usuario);

        return ResponseEntity.created(uri).body(new UserDetailsData(usuario));
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UserUpdateData dados ){
        
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados, passwordEncoder);
        
        return ResponseEntity.ok(new UserDetailsData(usuario));

    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable UUID id ) {

        var usuario = repository.getReferenceById(id);
        repository.delete(usuario);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable UUID id  ){
        
         var usuario = repository.getReferenceById(id);
         return ResponseEntity.ok(new UserDetailsData(usuario));
    }

    
}
