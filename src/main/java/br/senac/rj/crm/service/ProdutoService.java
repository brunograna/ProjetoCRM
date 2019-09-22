package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.ProdutoRepository;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
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

    public Produto findById(Integer id) throws ObjectNotFoundException {
        Optional<Produto> produto = repository.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto wih id ("+id+") not found"));
    }

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto save(Produto p){
        return repository.save(p);
    }

    public Produto update(Produto p) throws ObjectNotFoundException {
        Optional<Produto> produtoFromDB = repository.findById(p.getProdutoId());

        if(produtoFromDB.isPresent()){
            produtoFromDB.get().setProdutoStatus(p.getProdutoStatus());
            produtoFromDB.get().setProdutoNome(p.getProdutoNome());
            produtoFromDB.get().setProdutoDescricao(p.getProdutoDescricao());
            produtoFromDB.get().setNivelInstrucao(p.getNivelInstrucao());
            return produtoFromDB.get();
        }else{
            throw new ObjectNotFoundException("Produto Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<Produto> produtoFromDB = repository.findById(id);
        if(produtoFromDB.isPresent()){
            produtoFromDB.get().setProdutoStatus(false);
        }
    }

    public List<Produto> findAllActive() {
        return repository.findByProdutoStatus(true);
    }

    public long getTotalInNumber() {
        return repository.count();
    }
}
