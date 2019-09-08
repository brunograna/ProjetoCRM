package br.senac.rj.crm.init;

import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProdutoService produtoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Produto produto = new Produto();
        produto.setProdutoDescricao("Belo produto");
        produto.setProdutoStatus(true);

        produtoService.save(produto);

        List<Produto> produtos = produtoService.findAll();

        for (Produto produtoIndv : produtos) {
            System.out.println(produtoIndv.toString());
        }
    }
}
