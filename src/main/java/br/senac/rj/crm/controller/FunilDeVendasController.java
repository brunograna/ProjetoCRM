package br.senac.rj.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funil-de-vendas")
public class FunilDeVendasController {

    @GetMapping
    public ModelAndView funilDeVendas(){
        ModelAndView mv = new ModelAndView("/auth/funil-de-vendas/funil");

        return mv;
    }
}
