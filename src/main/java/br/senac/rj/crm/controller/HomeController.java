package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.dto.AcaoGraficoDto;
import br.senac.rj.crm.domain.dto.AcaoIndiceDto;
import br.senac.rj.crm.repository.AcaoUsuarioClienteOfertaRepository;
import br.senac.rj.crm.service.ClienteService;
import br.senac.rj.crm.service.OfertaService;
import br.senac.rj.crm.service.ProdutoService;
import br.senac.rj.crm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
    private List<AcaoGraficoDto> percentageOfAcao(){
        DecimalFormat df2 = new DecimalFormat("#.##");
        List<AcaoIndiceDto> percentageOfAcaoUsage = acaoUsuarioClienteOfertaRepository.findPercentageOfAcaoUsage();
        Long totalOfRows = acaoUsuarioClienteOfertaRepository.count();

        List<AcaoGraficoDto> acaoGraphic = new ArrayList<>();
        for (AcaoIndiceDto acao: percentageOfAcaoUsage) {
            acaoGraphic.add(new AcaoGraficoDto(acao.getAcao().getAcaoDescricao(), Double.parseDouble(df2.format(((double)acao.getQuantidade()/(double)totalOfRows)*100.0))));
        }
        System.out.println(acaoGraphic.toString());

        return acaoGraphic;
    }
}
