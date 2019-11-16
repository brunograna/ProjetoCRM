package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Cliente;
import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.ClienteOfertaId;
import br.senac.rj.crm.domain.Oferta;
import br.senac.rj.crm.domain.dto.ClienteOfertaDto;
import br.senac.rj.crm.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("cliente-oferta")
public class ClienteOfertaController {

    Logger logger = LoggerFactory.getLogger(ClienteOfertaController.class);

    @Autowired
    private FunilEtapaService funilEtapaService;

    @Autowired
    private ClienteOfertaDtoService clienteOfertaDtoService;

    @Autowired
    private ClienteOfertaService clienteOfertaService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ResponseBody
    public ClienteOfertaDto listFunil() throws JsonProcessingException {

        return clienteOfertaDtoService.getClienteOferta();
    }

    @GetMapping("/adicionar")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("/auth/cliente-oferta/cadastrarClienteOferta");
        mv.addObject("clienteOferta", new ClienteOferta());
        mv.addObject("ofertas", ofertaService.findAll());
        mv.addObject("clientes", clienteService.findAll());
        mv.addObject("funilEtapas", funilEtapaService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid ClienteOferta clienteOferta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("ClienteOferta on Add has errors validation");
            ModelAndView mv = new ModelAndView("/auth/cliente-oferta/cadastrarClienteOferta");
            mv.addObject("clienteOferta", clienteOferta);
            mv.addObject("ofertas", ofertaService.findAll());
            mv.addObject("clientes", clienteService.findAll());
            mv.addObject("funilEtapas", funilEtapaService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/funil-de-vendas");
        try {
            clienteOfertaService.save(clienteOferta);
        } catch (Exception e) {
            e.printStackTrace();
            mv = new ModelAndView("redirect:/cliente-oferta/adicionar?error=Preencha todos os campos corretamente");
        }
        return mv;
    }

    @GetMapping("/{clienteId}/{ofertaId}")
    public ModelAndView edit(@PathVariable("clienteId") Integer clienteId, @PathVariable("ofertaId") Integer ofertaId) {
        ModelAndView mv = new ModelAndView("/auth/cliente-oferta/alterarClienteOferta");
        try {
            Cliente cliente = clienteService.findById(clienteId);
            Oferta oferta = ofertaService.findById(ofertaId);
            ClienteOfertaId clienteOfertaId = new ClienteOfertaId(cliente, oferta);

            ClienteOferta clienteOfertaRetrieved = clienteOfertaService.findById(clienteOfertaId);
            mv.addObject("clienteOferta", clienteOfertaRetrieved);
            mv.addObject("ofertas", ofertaService.findAll());
            mv.addObject("clientes", clienteService.findAll());
            mv.addObject("funilEtapas", funilEtapaService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/funil-de-vendas?error=Não foi possível achar nenhuma oferta relacionada a esse cliente");
        }
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid ClienteOferta clienteOferta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("ClienteOferta on Edit has errors validation");
            ModelAndView mv = new ModelAndView("/auth/cliente-oferta/alterarClienteOferta");
            mv.addObject("clienteOferta", clienteOferta);
            mv.addObject("ofertas", ofertaService.findAll());
            mv.addObject("clientes", clienteService.findAll());
            mv.addObject("funilEtapas", funilEtapaService.findAll());
            return mv;
        }

        ModelAndView mv = new ModelAndView("redirect:/funil-de-vendas/");
        try {
            clienteOfertaService.update(clienteOferta);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/funil-de-vendas?error");
        }
        return mv;
    }

    @GetMapping("funil-update/{clienteId}/{ofertaId}/{funilEtapaId}")
    @ResponseBody
    public String updateClienteOfertaFunilEtapa(@PathVariable("clienteId") String clienteId,
                                                @PathVariable("ofertaId") String ofertaId,
                                                @PathVariable("funilEtapaId") String funilEtapaId) {
        try {
            clienteOfertaService.updateFunilEtapaByClienteIdAndOfertaId(Integer.parseInt(clienteId), Integer.parseInt(ofertaId), Integer.parseInt(funilEtapaId));
            return "{\"success\": true}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\": false}";
        }
    }

    @GetMapping("json/{clienteId}/{ofertaId}")
    @ResponseBody
    public ClienteOferta updateClienteOfertaFunilEtapa(@PathVariable("clienteId") String clienteId,
                                                @PathVariable("ofertaId") String ofertaId) {
        try {
            Cliente cliente = clienteService.findById(Integer.parseInt(clienteId));
            Oferta oferta = ofertaService.findById(Integer.parseInt(ofertaId));
            ClienteOfertaId clienteOfertaId = new ClienteOfertaId(cliente, oferta);

            ClienteOferta clienteOfertaRetrieved = clienteOfertaService.findById(clienteOfertaId);
            return clienteOfertaRetrieved;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}