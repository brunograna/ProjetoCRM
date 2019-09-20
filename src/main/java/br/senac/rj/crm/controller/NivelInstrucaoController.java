package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.service.NivelInstrucaoService;
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
@RequestMapping("niveis-instrucao")
public class NivelInstrucaoController {

    Logger logger = LoggerFactory.getLogger(NivelInstrucaoController.class);

    @Autowired
    private NivelInstrucaoService nivelInstrucaoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/niveis-instrucao/listarNiveisInstrucao");
        mv.addObject("niveisInstrucao", nivelInstrucaoService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid NivelInstrucao nivelInstrucao, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("NiveilInstrucao on Add has errors validation");
            ModelAndView mv = new ModelAndView("/auth/niveis-instrucao/cadastrarNiveisInstrucao");
            mv.addObject("nivelInstrucao", nivelInstrucao);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/niveis-instrucao");
        try{
            nivelInstrucaoService.save(nivelInstrucao);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/niveis-instrucao/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/niveis-instrucao/cadastrarNiveisInstrucao");
        mv.addObject("nivelInstrucao", new NivelInstrucao());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/niveis-instrucao/alterarNiveisInstrucao");
        try {
            NivelInstrucao nivelInstrucaoRetrieved = nivelInstrucaoService.findById(id);
            mv.addObject("nivelInstrucao", nivelInstrucaoRetrieved);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/niveis-instrucao?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid NivelInstrucao nivelInstrucao, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("NiveilInstrucao on Edit has errors validation");
            ModelAndView mv = new ModelAndView("/auth/niveis-instrucao/alterarNiveisInstrucao");
            mv.addObject("nivelInstrucao", nivelInstrucao);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/niveis-instrucao/");
        try {
            nivelInstrucaoService.update(nivelInstrucao);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/niveis-instrucao?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/niveis-instrucao");
        nivelInstrucaoService.softDelete(id);
        return mv;
    }



}
