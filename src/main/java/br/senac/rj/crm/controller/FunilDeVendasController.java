package br.senac.rj.crm.controller;

import br.senac.rj.crm.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funil-de-vendas")
public class FunilDeVendasController {

    @Autowired
    private AcaoService acaoService;

    @GetMapping
    public ModelAndView funilDeVendas(){
        ModelAndView mv = new ModelAndView("/auth/funil-de-vendas/funil");
        mv.addObject("acoes", acaoService.findAll());
        return mv;
    }
}
