package br.senac.rj.crm.init;

import br.senac.rj.crm.domain.*;
import br.senac.rj.crm.repository.*;
import br.senac.rj.crm.service.*;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private FunilEtapaService funilEtapaService;

    @Autowired
    private ClienteOfertaService clienteOfertaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private NivelInstrucaoService instrucaoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private CategoriaDadoRepository categoriaDadoRepository;

    @Autowired
    private DadoTipoRepository dadoTipoRepository;

    @Autowired
    private AcaoService acaoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initAdminUser();

        initFunilEtapa();

        initAcoes();

        try {
            initClienteOferta();
            initClienteOferta();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        initDadoTipo();
    }

    private void initAcoes() {
        Acao acao = new Acao();
        acao.setAcaoDescricao("Enviar Email");
        acao.setAcaoStatus(true);
        acaoService.save(acao);

        acao = new Acao();
        acao.setAcaoDescricao("Telefonar");
        acao.setAcaoStatus(true);
        acaoService.save(acao);
    }

    private void initAdminUser(){
        Usuario usuario = new Usuario();

        usuario.setUsuarioCargo("Cargo");
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setUsuarioNome("admin");
        usuario.setUsuarioStatus(true);

        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setPerfilUsuarioStatus(true);
        perfil.setPerfilUsuarioDescricao("Atendente");

        perfilUsuarioRepository.save(perfil);

        List<PerfilUsuario> perfis = new ArrayList<>();
        perfis.add(perfil);
        usuario.setPerfilUsuario(perfil);

        usuarioRepository.save(usuario);
    }

    private void initClienteOferta() throws ObjectNotFoundException {
        Produto produto = new Produto();
        produto.setProdutoStatus(true);
        produto.setProdutoNome("Análise e Desenvolvimento de Sistemas");
        produto.setProdutoDescricao("Curso da Faculdade Senac Rio");

        NivelInstrucao instrucao = new NivelInstrucao();
        instrucao.setNivelInstrucaoStatus(true);
        instrucao.setNivelInstrucaoDescricao("Ensino Médio");
        instrucao = instrucaoService.save(instrucao);

        produto.setNivelInstrucao(instrucao);
        produto = produtoService.save(produto);

        Cliente cliente = new Cliente();
        cliente.setClienteStatus(true);
        cliente.setClienteCpf("12334556711");
        cliente.setClienteEmail("cliente@email.com");
        cliente.setClienteNome("Cliente");
        cliente.setClienteSobrenome("Do Senac");
        cliente = clienteService.save(cliente);

        Oferta oferta = new Oferta();
        oferta.setOfertaStatus(true);
        oferta.setOfertaDataInicio(LocalDate.now());
        oferta.setOfertaDataFim(LocalDate.now().plusDays(7));
        oferta.setOfertaDescricao("Oferta de curso");
        oferta.setOfertaProduto(produto);
        oferta.setOfertaPreco(599);

        ofertaService.save(oferta);

        ClienteOferta clienteOferta = new ClienteOferta();
        clienteOferta.setClienteOfertaPrecoDescricao("Descricao do cliente oferta");
        clienteOferta.setClienteOfertaPreco(550.59);
        clienteOferta.setClienteOfertaStatus(true);
        clienteOferta.setFunilEtapa(funilEtapaService.findById(1));

        ClienteOfertaId clienteOfertaId = new ClienteOfertaId();
        clienteOfertaId.setCliente(cliente);
        clienteOfertaId.setOferta(oferta);

        clienteOferta.setClienteOfertaId(clienteOfertaId);
        clienteOfertaService.save(clienteOferta);
    }

    private void initFunilEtapa(){
        FunilEtapa funilEtapa = new FunilEtapa();
        funilEtapa.setFunilEtapaStatus(true);

        funilEtapa.setFunilEtapaDescricao("Visitante");
        funilEtapaService.save(funilEtapa);

        funilEtapa = new FunilEtapa();
        funilEtapa.setFunilEtapaDescricao("Lead");
        funilEtapa.setFunilEtapaStatus(true);
        funilEtapaService.save(funilEtapa);

        funilEtapa = new FunilEtapa();
        funilEtapa.setFunilEtapaDescricao("Inscrito parcial");
        funilEtapa.setFunilEtapaStatus(true);
        funilEtapaService.save(funilEtapa);

        funilEtapa = new FunilEtapa();
        funilEtapa.setFunilEtapaDescricao("Matriculado");
        funilEtapa.setFunilEtapaStatus(true);
        funilEtapaService.save(funilEtapa);
    }

    private void initDadoTipo() {
        CategoriaDado categoriaDado = new CategoriaDado();
        categoriaDado.setCategoriaDadoStatus(true);
        categoriaDado.setCategoriaDadoDescricao("Dados Pessoais");
        categoriaDadoRepository.save(categoriaDado);

        DadoTipo dadoTipo = new DadoTipo();
        dadoTipo.setDadoTipoStatus(true);
        dadoTipo.setDadoTipoPadrao("");
        dadoTipo.setDadoTipoObrigatorio(true);
        dadoTipo.setDadoTipoDescricao("Passaporte");
        dadoTipo.setCategoria(categoriaDado);
        dadoTipoRepository.save(dadoTipo);

        DadoTipo dadoTipo2 = new DadoTipo();
        dadoTipo2.setDadoTipoStatus(true);
        dadoTipo2.setDadoTipoObrigatorio(true);
        dadoTipo2.setDadoTipoPadrao("");
        dadoTipo2.setDadoTipoDescricao("CRM");
        dadoTipo2.setCategoria(categoriaDado);
        dadoTipoRepository.save(dadoTipo2);
    }
}
