package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto save(Produto p){
        return repository.save(p);
    }
}
