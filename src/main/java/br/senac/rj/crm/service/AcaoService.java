package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Acao;
import br.senac.rj.crm.repository.AcaoRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AcaoService {

    @Autowired
    private AcaoRepository repository;

    public Acao findById(Integer id) throws ObjectNotFoundException {
        Optional<Acao> acao = repository.findById(id);

        return acao.orElseThrow(() -> new ObjectNotFoundException("Acao wih id ("+id+") not found"));
    }

    public List<Acao> findAll(){
        return repository.findAll();
    }

    public Acao save(Acao a){
        return repository.save(a);
    }

    public Acao update(Acao a) throws ObjectNotFoundException {
        Optional<Acao> acaoFromDB = repository.findById(a.getAcaoId());

        if(acaoFromDB.isPresent()){
            acaoFromDB.get().setAcaoDescricao(a.getAcaoDescricao());
            acaoFromDB.get().setAcaoStatus(a.getAcaoStatus());

            return acaoFromDB.get();
        }else{
            throw new ObjectNotFoundException("Acao Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<Acao> acaoFromDB = repository.findById(id);
        if(acaoFromDB.isPresent()){
            acaoFromDB.get().setAcaoStatus(false);
        }
    }

}