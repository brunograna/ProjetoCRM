package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.*;
import br.senac.rj.crm.domain.dto.ClienteOfertaDto;
import br.senac.rj.crm.domain.dto.TimelineDto;
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
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AcaoUsuarioClienteOfertaService acaoUsuarioClienteOfertaService;

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
    public ClienteOferta getClienteOferta(@PathVariable("clienteId") String clienteId,
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

    @PostMapping("json/acao")
    @ResponseBody
    public String saveAcaoUsuarioClienteOferta(@RequestBody @RequestParam("clienteId") String clienteId,
                                               @RequestBody @RequestParam("ofertaId") String ofertaId,
                                               @RequestBody @RequestParam("acaoId") String acaoId,
                                               @RequestBody @RequestParam("descricao") String descricao,
                                               @RequestBody @RequestParam("data") String data,
                                               Principal principal) {
        try {
            Cliente cliente = clienteService.findById(Integer.parseInt(clienteId));
            Oferta oferta = ofertaService.findById(Integer.parseInt(ofertaId));
            Acao acao = acaoService.findById(Integer.parseInt(acaoId));
            ClienteOfertaId clienteOfertaId = new ClienteOfertaId(cliente, oferta);
            ClienteOferta clienteOferta = clienteOfertaService.findById(clienteOfertaId);

            //Format date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            AcaoUsuarioClienteOferta acaoUsuarioClienteOferta = new AcaoUsuarioClienteOferta();
            acaoUsuarioClienteOferta.setClienteOferta(clienteOferta);
            acaoUsuarioClienteOferta.setAcao(acao);
            acaoUsuarioClienteOferta.setAcaoUsuarioClienteOfertaDescricao(descricao);
            acaoUsuarioClienteOferta.setAcaoUsuarioClienteOfertaData(LocalDate.parse(data, formatter));
            acaoUsuarioClienteOferta.setUsuario(usuarioService.findByUsername(principal.getName()));

            acaoUsuarioClienteOfertaService.save(acaoUsuarioClienteOferta);

            return "{\"success\": true}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\": false}";
        }
    }

    @GetMapping("/timeline/{clienteId}/{ofertaId}")
    @ResponseBody
    public List<TimelineDto> timelineForClienteOferta(@PathVariable("clienteId") String clienteId,
                                                                   @PathVariable("ofertaId") String ofertaId) {

        try {
            Cliente cliente = clienteService.findById(Integer.parseInt(clienteId));
            Oferta oferta = ofertaService.findById(Integer.parseInt(ofertaId));
            ClienteOfertaId clienteOfertaId = new ClienteOfertaId(cliente, oferta);

            //Format date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            ClienteOferta clienteOfertaRetrieved = clienteOfertaService.findById(clienteOfertaId);
            List<AcaoUsuarioClienteOferta> timeline = acaoUsuarioClienteOfertaService.getTimelineForClienteOferta(clienteOfertaRetrieved);
            List<TimelineDto> timelineDto = new ArrayList<>();
            TimelineDto timelineItem;
            for (AcaoUsuarioClienteOferta acaoUsuarioClienteOferta: timeline){
                timelineItem = new TimelineDto();
                timelineItem.setAcao(acaoUsuarioClienteOferta.getAcao().getAcaoDescricao());
                timelineItem.setAutor(StringUtils.capitalize(acaoUsuarioClienteOferta.getUsuario().getUsuarioNome()));
                timelineItem.setData(formatter.format(acaoUsuarioClienteOferta.getAcaoUsuarioClienteOfertaData()));
                timelineItem.setDescricao(acaoUsuarioClienteOferta.getAcaoUsuarioClienteOfertaDescricao());

                timelineDto.add(timelineItem);
            }

            return timelineDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}