package pgp.projeto.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    
    //nessa pagina terá uma breve apresentação da aplicação e um botão para redirecionar para a pagina de login
    @GetMapping("/")
    public ModelAndView paginaInicial(){
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("mensagem", "Bem-vindo ao sistema de gerenciamento de projetos");
        var alunos = List.of("Aluno 1", "Aluno 2", "Aluno 3");
        modelAndView.addObject("alunos", alunos);
        return modelAndView;
    }
}
