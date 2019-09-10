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

    public NivelInstrucao update(NivelInstrucao instrucao) throws NotFoundException {
        Optional<NivelInstrucao> instrucaoFromDB = repository.findById(instrucao.getInstrucaoId());

        if(instrucaoFromDB.isPresent()){
            instrucaoFromDB.get().setInstrucaoStatus(instrucao.getInstrucaoStatus());
            instrucaoFromDB.get().setInstrucaoDescricao(instrucao.getInstrucaoDescricao());
            return instrucaoFromDB.get();
        }else{
            throw new NotFoundException("NivelInstrucao Not Found");
        }
    }

    public void softDelete(NivelInstrucao instrucao) {
        Optional<NivelInstrucao> instrucaoFromDB = repository.findById(instrucao.getInstrucaoId());
        if(instrucaoFromDB.isPresent()){
            instrucaoFromDB.get().setInstrucaoStatus(false);
        }
    }

}
