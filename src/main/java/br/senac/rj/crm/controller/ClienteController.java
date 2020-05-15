package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Cliente;
import br.senac.rj.crm.domain.ClienteDado;
import br.senac.rj.crm.domain.DadoTipo;
import br.senac.rj.crm.service.CategoriaDadoService;
import br.senac.rj.crm.service.ClienteService;
import br.senac.rj.crm.service.DadoTipoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DadoTipoService dadoTipoService;

    @Autowired
    private CategoriaDadoService categoriaDadoService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/clientes/listarClientes");
        mv.addObject("clientes", clienteService.findAll());
        return mv;
    }

    @PostMapping()
    public ModelAndView save(@Valid Cliente cliente, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("Has errors");
            return new ModelAndView("/auth/clientes/cadastrarClientes");
        }

        ModelAndView mv = new ModelAndView("redirect:/clientes");
        try{
            clienteService.save(cliente);
        }catch (Exception e){
            e.printStackTrace();
            mv = new ModelAndView("redirect:/clientes/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/clientes/cadastrarClientes");
        mv.addObject("cliente", new Cliente());
        mv.addObject("dadoTipoList", dadoTipoService.findAll());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/clientes/alterarClientes");
        try {
            Cliente clienteRetrieved = clienteService.findById(id);
            mv.addObject("cliente", clienteRetrieved);
            mv.addObject("dadoTipoList", dadoTipoService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/clientes?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid Cliente cliente, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/auth/clientes/alterarClientes");
            mv.addObject("cliente", cliente);
            mv.addObject("dadoTipoList", dadoTipoService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/clientes/");
        try {
            clienteService.update(cliente);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/clientes?error");
        }
        return mv;
    }

    @PostMapping(value="/edit", params={"addClienteDado"})
    public ModelAndView addClienteDadoEdit(final Cliente cliente, final BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("/auth/clientes/alterarClientes");
        cliente.getClienteDado().add(new ClienteDado());

        mv.addObject("cliente", cliente);
        mv.addObject("dadoTipoList", dadoTipoService.findAll());

        return mv;
    }

    @PostMapping(value="/edit", params={"removeClienteDado"})
    public ModelAndView removeClienteDadoEdit(
            final Cliente cliente, final BindingResult bindingResult,
            final HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("/auth/clientes/alterarClientes");

        final Integer rowId = Integer.valueOf(req.getParameter("removeClienteDado"));
        cliente.getClienteDado().remove(rowId.intValue());

        mv.addObject("cliente", cliente);
        mv.addObject("dadoTipoList", dadoTipoService.findAll());

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/clientes");
        clienteService.softDelete(id);
        return mv;
    }

    @PostMapping(value="", params={"addClienteDado"})
    public ModelAndView addClienteDado(final Cliente cliente, final BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("/auth/clientes/cadastrarClientes");
        cliente.getClienteDado().add(new ClienteDado());

        mv.addObject("cliente", cliente);
        mv.addObject("dadoTipoList", dadoTipoService.findAll());

        return mv;
    }

    @PostMapping(value="", params={"removeClienteDado"})
    public ModelAndView removeClienteDado(
            final Cliente cliente, final BindingResult bindingResult,
            final HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("/auth/clientes/cadastrarClientes");

        final Integer rowId = Integer.valueOf(req.getParameter("removeClienteDado"));
        cliente.getClienteDado().remove(rowId.intValue());

        mv.addObject("cliente", cliente);
        mv.addObject("dadoTipoList", dadoTipoService.findAll());

        return mv;
    }



}