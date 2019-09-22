package br.senac.rj.crm.controller;

import br.senac.rj.crm.service.ClienteService;
import br.senac.rj.crm.service.OfertaService;
import br.senac.rj.crm.service.ProdutoService;
import br.senac.rj.crm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("redirect:/dashboard");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Authentication authentication){
        ModelAndView mv = new ModelAndView("/auth/dashboard");
        mv.addObject("clientes", clienteService.getTotalInNumber());
        mv.addObject("ofertas", ofertaService.getTotalInNumber());
        mv.addObject("usuarios", usuarioService.getTotalInNumber());
        return mv;
    }
}
