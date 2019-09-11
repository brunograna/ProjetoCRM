package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("/auth/clientes/listarClientes");
        return mv;
    }

    @PostMapping
    public ModelAndView save(@RequestBody Cliente cliente){
        ModelAndView mv = new ModelAndView("redirect:/clientes");

        return mv;
    }



}