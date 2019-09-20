package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.NivelInstrucaoRepository;
import br.senac.rj.crm.repository.ProdutoRepository;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NivelInstrucaoService {

    @Autowired
    private NivelInstrucaoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public NivelInstrucao findById(Integer id) throws ObjectNotFoundException {
        Optional<NivelInstrucao> instrucao = repository.findById(id);

        return instrucao.orElseThrow(() -> new ObjectNotFoundException("NivelInstrucao wih id ("+id+") not found"));
    }

    public List<NivelInstrucao> findAll(){
        return repository.findAll();
    }

    public NivelInstrucao save(NivelInstrucao instrucao){
        return repository.save(instrucao);
    }

    public NivelInstrucao update(NivelInstrucao instrucao) throws ObjectNotFoundException {
        Optional<NivelInstrucao> instrucaoFromDB = repository.findById(instrucao.getNivelInstrucaoId());

        if(instrucaoFromDB.isPresent()){
            instrucaoFromDB.get().setNivelInstrucaoStatus(instrucao.getNivelInstrucaoStatus());
            instrucaoFromDB.get().setNivelInstrucaoDescricao(instrucao.getNivelInstrucaoDescricao());
            return instrucaoFromDB.get();
        }else{
            throw new ObjectNotFoundException("NivelInstrucao Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<NivelInstrucao> instrucaoFromDB = repository.findById(id);
        if(instrucaoFromDB.isPresent()){
            instrucaoFromDB.get().setNivelInstrucaoStatus(false);
        }
    }

}
