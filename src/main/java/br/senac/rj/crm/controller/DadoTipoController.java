package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.DadoTipo;
import br.senac.rj.crm.service.CategoriaDadoService;
import br.senac.rj.crm.service.DadoTipoService;
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
@RequestMapping("dados-tipos")
public class DadoTipoController {

    Logger logger = LoggerFactory.getLogger(DadoTipoController.class);

    @Autowired
    private DadoTipoService dadoTipoService;

    @Autowired
    private CategoriaDadoService categoriaDadoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/dados-tipos/listarDadoTipos");
        mv.addObject("dadoTipos", dadoTipoService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid DadoTipo dadoTipo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("DadoTipo on Add has errors validation:\n"+bindingResult.getAllErrors());
            ModelAndView mv = new ModelAndView("/auth/dados-tipos/cadastrarDadoTipos");
            mv.addObject("dadoTipo", dadoTipo);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/dados-tipos");
        try{
            dadoTipoService.save(dadoTipo);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/dados-tipos/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/dados-tipos/cadastrarDadoTipos");
        mv.addObject("dadoTipo", new DadoTipo());
        mv.addObject("categorias", categoriaDadoService.findAll());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/dados-tipos/alterarDadoTipos");
        try {
            DadoTipo dadoRetrieved = dadoTipoService.findById(id);
            mv.addObject("dadoTipo", dadoRetrieved);
            mv.addObject("categorias", categoriaDadoService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/dados-tipos?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid DadoTipo dadoTipo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("DadoTipo on Edit has errors validation: \n"+bindingResult.getAllErrors());
            ModelAndView mv = new ModelAndView("/auth/dados-tipos/alterarDadoTipos");
            mv.addObject("dadoTipo", dadoTipo);
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/dados-tipos/");
        try {
            dadoTipoService.update(dadoTipo);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/dados-tipos?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/dados-tipos");
        dadoTipoService.softDelete(id);
        return mv;
    }

}
