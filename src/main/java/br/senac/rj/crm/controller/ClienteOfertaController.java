package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.dto.ClienteOfertaDto;
import br.senac.rj.crm.service.ClienteOfertaDtoService;
import br.senac.rj.crm.service.ClienteOfertaService;
import br.senac.rj.crm.service.FunilEtapaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("cliente-oferta")
public class ClienteOfertaController {

    @Autowired
    private FunilEtapaService funilEtapaService;

    @Autowired
    private ClienteOfertaDtoService clienteOfertaDtoService;

    @Autowired
    private ClienteOfertaService clienteOfertaService;

    @GetMapping
    @ResponseBody
    public ClienteOfertaDto listFunil() throws JsonProcessingException {

        return clienteOfertaDtoService.getClienteOferta();
    }

    @GetMapping("funil-update/{clienteId}/{ofertaId}/{funilEtapaId}")
    @ResponseBody
    public String updateClienteOfertaFunilEtapa(@PathVariable("clienteId") String clienteId,
                                                @PathVariable("ofertaId") String ofertaId,
                                                @PathVariable("funilEtapaId") String funilEtapaId){
        try{
            clienteOfertaService.updateFunilEtapaByClienteIdAndOfertaId(Integer.parseInt(clienteId), Integer.parseInt(ofertaId), Integer.parseInt(funilEtapaId));
            return "{\"success\": true}";
        }catch (Exception e){
            return "{\"success\": false}";
        }
    }
}
