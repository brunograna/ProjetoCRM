package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.DadoTipo;
import br.senac.rj.crm.repository.DadoTipoRepository;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DadoTipoService {

    @Autowired
    private DadoTipoRepository repository;

    public DadoTipo findById(Integer id) throws ObjectNotFoundException {
        Optional<DadoTipo> dadoTipo = repository.findById(id);

        return dadoTipo.orElseThrow(() -> new ObjectNotFoundException("DadoTipo wih id ("+id+") not found"));
    }

    public List<DadoTipo> findAll(){
        return repository.findAll();
    }

    public DadoTipo save(DadoTipo c){
        return repository.save(c);
    }

    public DadoTipo update(DadoTipo dado) throws ObjectNotFoundException {
        Optional<DadoTipo> dadoFromDB = repository.findById(dado.getDadoTipoId());

        if(dadoFromDB.isPresent()){
            dadoFromDB.get().setCategoria(dado.getCategoria());
            dadoFromDB.get().setDadoTipoDescricao(dado.getDadoTipoDescricao());
            dadoFromDB.get().setDadoTipoMascara(dado.getDadoTipoMascara());
            dadoFromDB.get().setDadoTipoObrigatorio(dado.getDadoTipoObrigatorio());
            dadoFromDB.get().setDadoTipoPadrao(dado.getDadoTipoPadrao());
            dadoFromDB.get().setDadoTipoStatus(dado.getDadoTipoStatus());

            return dadoFromDB.get();
        }else{
            throw new ObjectNotFoundException("DadoTipo Not Found");
        }
    }

    public void softDelete(DadoTipo dado) {
        Optional<DadoTipo> dadoFromDB = repository.findById(dado.getDadoTipoId());
        if(dadoFromDB.isPresent()){
            dadoFromDB.get().setDadoTipoStatus(false);
        }
    }
}
