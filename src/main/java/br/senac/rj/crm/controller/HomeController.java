package br.senac.rj.crm.controller;

import br.senac.rj.crm.repository.ClienteOfertaRepository;
import br.senac.rj.crm.repository.dto.AcaoIndiceDto;
import br.senac.rj.crm.domain.dto.ChartJsDto;
import br.senac.rj.crm.repository.AcaoUsuarioClienteOfertaRepository;
import br.senac.rj.crm.repository.dto.ClienteEtapaValorDto;
import br.senac.rj.crm.service.ClienteService;
import br.senac.rj.crm.service.OfertaService;
import br.senac.rj.crm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AcaoUsuarioClienteOfertaRepository acaoUsuarioClienteOfertaRepository;

    @Autowired
    private ClienteOfertaRepository clienteOfertaRepository;

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("redirect:/dashboard");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Authentication authentication){
        ModelAndView mv = new ModelAndView("/auth/dashboard");

        mv.addObject("clientes", clienteService.getTotalInNumber());
        mv.addObject("ofertas", ofertaService.getTotalInNumber());
        mv.addObject("usuarios", usuarioService.getTotalInNumber());
        return mv;
    }

    @RequestMapping("/acao-indice")
    @ResponseBody
    private ChartJsDto percentageOfAcao(){
        DecimalFormat df2 = new DecimalFormat("#.##");
        List<AcaoIndiceDto> percentageOfAcaoUsage = acaoUsuarioClienteOfertaRepository.findPercentageOfAcaoUsage();
        Long totalOfRows = acaoUsuarioClienteOfertaRepository.count();

        ChartJsDto acaoGraphic = new ChartJsDto();
        for (AcaoIndiceDto acao: percentageOfAcaoUsage) {

            acaoGraphic.addLabel(acao.getAcao().getAcaoDescricao());
            acaoGraphic.addData((double)acao.getQuantidade());
            acaoGraphic.addColor(getRandomColor());
        }

        return acaoGraphic;
    }

    @RequestMapping("/cliente-valor")
    @ResponseBody
    private ChartJsDto clienteValor(){
        List<ClienteEtapaValorDto> funilEtapaClienteValor = clienteOfertaRepository.getFunilEtapaClienteValor();

        ChartJsDto funilEtapaGraphic = new ChartJsDto();
        for (ClienteEtapaValorDto etapaValorDto: funilEtapaClienteValor) {

            funilEtapaGraphic.addLabel(etapaValorDto.getFunilEtapa().getFunilEtapaDescricao());
            funilEtapaGraphic.addBarLabel("Valor do cliente na etapa do funil");
            funilEtapaGraphic.addData((double)etapaValorDto.getValor());
            funilEtapaGraphic.addColor(getRandomColor());
        }

        return funilEtapaGraphic;
    }

    private String getRandomColor(){
        // create random object - reuse this as often as possible
        Random random = new Random();

        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }
}
