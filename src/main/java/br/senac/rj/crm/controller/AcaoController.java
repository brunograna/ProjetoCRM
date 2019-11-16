package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Acao;
import br.senac.rj.crm.service.AcaoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("acoes")
public class AcaoController {

    Logger logger = LoggerFactory.getLogger(AcaoController.class);

    @Autowired
    private AcaoService acaoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/acoes/listarAcoes");
        mv.addObject("acoes", acaoService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid Acao acao, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Acao on Add has errors validation");
            ModelAndView mv = new ModelAndView("/auth/acoes/cadastrarAcoes");
            mv.addObject("acao", acao);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/acoes");
        try{
            acaoService.save(acao);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/acoes/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/acoes/cadastrarAcoes");
        mv.addObject("acao", new Acao());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/acoes/alterarAcoes");
        try {
            Acao acaoRetrieved = acaoService.findById(id);
            mv.addObject("acao", acaoRetrieved);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/acoes?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid Acao acao, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Acao on Edit has errors validation");
            ModelAndView mv = new ModelAndView("/auth/acoes/alterarAcoes");
            mv.addObject("acao", acao);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/acoes/");
        try {
            acaoService.update(acao);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/acoes?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/acoes");
        acaoService.softDelete(id);
        return mv;
    }
}
