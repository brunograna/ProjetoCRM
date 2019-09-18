package br.senac.rj.crm.init;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.domain.PerfilUsuario;
import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.domain.Usuario;
import br.senac.rj.crm.repository.PerfilUsuarioRepository;
import br.senac.rj.crm.repository.ProdutoRepository;
import br.senac.rj.crm.repository.UsuarioRepository;
import br.senac.rj.crm.service.NivelInstrucaoService;
import br.senac.rj.crm.service.ProdutoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private NivelInstrucaoService instrucaoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Produto produto = new Produto();
        produto.setProdutoDescricao("Belo produto");
        produto.setProdutoStatus(true);

        NivelInstrucao instrucao = new NivelInstrucao();
        instrucao.setInstrucaoDescricao("Instrucao");
        instrucao.setInstrucaoStatus(true);

        instrucao = instrucaoService.save(instrucao);

        produto.setNivelInstrucao(instrucao);

        produto = produtoService.save(produto);

        List<Produto> produtos = produtoService.findAll();

        for (Produto produtoIndv : produtos) {
            System.out.println(produtoIndv.toString());
        }

        produtoService.softDelete(produto.getProdutoId());
        instrucaoService.softDelete(instrucao.getInstrucaoId());

        Usuario usuario = new Usuario();

        usuario.setUsuarioCargo("Cargo");
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setUsuarioNome("admin");
        usuario.setUsuarioStatus(true);

        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setPerfilUsuarioStatus(true);
        perfil.setPerfilUsuarioDescricao("ATENDENTE");

        perfilUsuarioRepository.save(perfil);

//        List<PerfilUsuario> perfis = new ArrayList<>();
//        perfis.add(perfil);
        usuario.setPerfilUsuario(perfil);

        usuarioRepository.save(usuario);
    }
}
