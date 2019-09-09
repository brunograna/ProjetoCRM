package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.ProdutoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto save(Produto p){
        return repository.save(p);
    }

    public Produto update(Produto p) throws NotFoundException {
        Optional<Produto> produtoFromDB = repository.findById(p.getProdutoId());

        if(produtoFromDB.isPresent()){
            produtoFromDB.get().setProdutoStatus(p.getProdutoStatus());
            produtoFromDB.get().setProdutoDescricao(p.getProdutoDescricao());
            produtoFromDB.get().setNivelInstrucao(p.getNivelInstrucao());
            return produtoFromDB.get();
        }else{
            throw new NotFoundException("Produto Not Found");
        }
    }

    public void delete(Produto p) {
        Optional<Produto> produtoFromDB = repository.findById(p.getProdutoId());
        if(produtoFromDB.isPresent()){
            produtoFromDB.get().setProdutoStatus(false);
        }
    }
}
