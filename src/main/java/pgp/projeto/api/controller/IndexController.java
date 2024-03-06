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
        return modelAndView;
    }
}
