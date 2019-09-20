package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Oferta;
import br.senac.rj.crm.service.OfertaService;
import br.senac.rj.crm.service.ProdutoService;
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
@RequestMapping("ofertas")
public class OfertaController {

    Logger logger = LoggerFactory.getLogger(OfertaController.class);

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/ofertas/listarOfertas");
        mv.addObject("ofertas", ofertaService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid Oferta oferta, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Oferta on Add has errors validation");
            ModelAndView mv = new ModelAndView("/auth/ofertas/cadastrarOfertas");
            mv.addObject("oferta", oferta);
            mv.addObject("produtos", produtoService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/ofertas");
        try{
            ofertaService.save(oferta);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/ofertas/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/ofertas/cadastrarOfertas");
        mv.addObject("oferta", new Oferta());
        mv.addObject("produtos", produtoService.findAllActive());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/ofertas/alterarOfertas");
        try {
            Oferta ofertaRetrieved = ofertaService.findById(id);
            mv.addObject("oferta", ofertaRetrieved);
            mv.addObject("produtos", produtoService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/ofertas?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid Oferta oferta, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Oferta on Edit has errors validation");
            ModelAndView mv = new ModelAndView("/auth/ofertas/alterarOfertas");
            mv.addObject("oferta", oferta);
            mv.addObject("produtos", produtoService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/ofertas/");
        try {
            ofertaService.update(oferta);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/ofertas?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/ofertas");
        ofertaService.softDelete(id);
        return mv;
    }
}
