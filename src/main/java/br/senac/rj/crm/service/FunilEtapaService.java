package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.FunilEtapa;
import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.repository.FunilEtapaRepository;
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
public class FunilEtapaService {

    @Autowired
    private FunilEtapaRepository repository;


    public FunilEtapa findById(Integer id) throws ObjectNotFoundException {
        Optional<FunilEtapa> funil = repository.findById(id);

        return funil.orElseThrow(() -> new ObjectNotFoundException("FunilEtapa wih id ("+id+") not found"));
    }

    public List<FunilEtapa> findAll(){
        return repository.findAll();
    }

    public FunilEtapa save(FunilEtapa funil){
        return repository.save(funil);
    }

    public FunilEtapa update(FunilEtapa funil) throws ObjectNotFoundException {
        Optional<FunilEtapa> funilFromDB = repository.findById(funil.getFunilEtapaId());

        if(funilFromDB.isPresent()){
            funilFromDB.get().setFunilEtapaStatus(funil.getFunilEtapaStatus());
            funilFromDB.get().setFunilEtapaDescricao(funil.getFunilEtapaDescricao());
            return funilFromDB.get();
        }else{
            throw new ObjectNotFoundException("FunilEtapa Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<FunilEtapa> funilFromDB = repository.findById(id);
        if(funilFromDB.isPresent()){
            funilFromDB.get().setFunilEtapaStatus(false);
        }
    }

    public List<FunilEtapa> findAllActive() {
        return repository.findByFunilEtapaStatus(true);
    }
}
