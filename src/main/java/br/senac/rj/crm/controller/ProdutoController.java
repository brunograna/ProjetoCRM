package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.ProdutoRepository;
import br.senac.rj.crm.service.NivelInstrucaoService;
import br.senac.rj.crm.service.ProdutoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

    Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private NivelInstrucaoService nivelInstrucaoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/produtos/listarProdutos");
        mv.addObject("produtos", produtoService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid Produto produto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Produto on Add has errors validation:\n"+bindingResult.getAllErrors());
            ModelAndView mv = new ModelAndView("/auth/produtos/cadastrarProdutos");
            mv.addObject("produto", produto);
            mv.addObject("niveisInstrucao", nivelInstrucaoService.findAllActive());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/produtos");
        try{
            produtoService.save(produto);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/produtos/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/produtos/cadastrarProdutos");
        mv.addObject("produto", new Produto());
        mv.addObject("niveisInstrucao", nivelInstrucaoService.findAllActive());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/produtos/alterarProdutos");
        try {
            Produto userRetrieved = produtoService.findById(id);
            mv.addObject("produto", userRetrieved);
            mv.addObject("niveisInstrucao", nivelInstrucaoService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/produtos?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid Produto produto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.warn("Produto on Edit has errors validation: \n"+bindingResult.getAllErrors());
            ModelAndView mv = new ModelAndView("/auth/produtos/alterarProdutos");
            mv.addObject("produto", produto);
            mv.addObject("niveisInstrucao", nivelInstrucaoService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/produtos/");
        try {
            produtoService.update(produto);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/produtos?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/produtos");
        produtoService.softDelete(id);
        return mv;
    }
}
