package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.AcaoUsuarioCliente;
import br.senac.rj.crm.repository.AcaoUsuarioClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AcaoUsuarioClienteService {

    @Autowired
    private AcaoUsuarioClienteRepository repository;

    public AcaoUsuarioCliente findById(Integer id) throws ObjectNotFoundException {
        Optional<AcaoUsuarioCliente> acaoUsuarioCliente = repository.findById(id);

        return acaoUsuarioCliente.orElseThrow(() -> new ObjectNotFoundException("AcaoUsuarioCliente wih id ("+id+") not found"));
    }

    public List<AcaoUsuarioCliente> findAll(){
        return repository.findAll();
    }

    public AcaoUsuarioCliente save(AcaoUsuarioCliente a){
        return repository.save(a);
    }

    public AcaoUsuarioCliente update(AcaoUsuarioCliente a) throws ObjectNotFoundException {
        Optional<AcaoUsuarioCliente> acaoUsuarioClienteFromDB = repository.findById(a.getAcaoUsuarioClienteId());

        if(acaoUsuarioClienteFromDB.isPresent()){
            acaoUsuarioClienteFromDB.get().setAcaoUsuarioClienteDescricao(a.getAcaoUsuarioClienteDescricao());
            acaoUsuarioClienteFromDB.get().setAcao(a.getAcao());
            acaoUsuarioClienteFromDB.get().setAcaoUsuarioClienteData(a.getAcaoUsuarioClienteData());
            acaoUsuarioClienteFromDB.get().setCliente(a.getCliente());
            acaoUsuarioClienteFromDB.get().setUsuario(a.getUsuario());

            return acaoUsuarioClienteFromDB.get();
        }else{
            throw new ObjectNotFoundException("AcaoUsuarioCliente Not Found");
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
