package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    long count();
    List<Produto> findByProdutoStatus(boolean status);
}
