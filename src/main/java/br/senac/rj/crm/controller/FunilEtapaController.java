package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.FunilEtapa;
import br.senac.rj.crm.service.FunilEtapaService;
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

@Controller
@RequestMapping("funil-etapa")
public class FunilEtapaController {

    Logger logger = LoggerFactory.getLogger(FunilEtapaController.class);

    @Autowired
    private FunilEtapaService funilEtapaService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/funil-etapa/listarFunilEtapa");
        mv.addObject("funilEtapas", funilEtapaService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid FunilEtapa funilEtapa, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("FunilEtapa on Add has errors validation");
            ModelAndView mv = new ModelAndView("/auth/funil-etapa/cadastrarFunilEtapa");
            mv.addObject("funilEtapa", funilEtapa);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/funil-etapa");
        try{
            funilEtapaService.save(funilEtapa);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/funil-etapa/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/funil-etapa/cadastrarFunilEtapa");
        mv.addObject("funilEtapa", new FunilEtapa());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/funil-etapa/alterarFunilEtapa");
        try {
            FunilEtapa funilEtapaRetrieved = funilEtapaService.findById(id);
            mv.addObject("funilEtapa", funilEtapaRetrieved);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/funil-etapa?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid FunilEtapa funilEtapa, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("FunilEtapa on Edit has errors validation");
            ModelAndView mv = new ModelAndView("/auth/funil-etapa/alterarFunilEtapa");
            mv.addObject("funilEtapa", funilEtapa);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/funil-etapa/");
        try {
            funilEtapaService.update(funilEtapa);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/funil-etapa?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/funil-etapa");
        funilEtapaService.softDelete(id);
        return mv;
    }
}
