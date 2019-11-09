package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.AcaoUsuarioClienteOferta;
import br.senac.rj.crm.repository.AcaoUsuarioClienteOfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AcaoUsuarioClienteOfertaService {

    @Autowired
    private AcaoUsuarioClienteOfertaRepository repository;

    public AcaoUsuarioClienteOferta findById(Integer id) throws ObjectNotFoundException {
        Optional<AcaoUsuarioClienteOferta> acaoUsuarioClienteOferta = repository.findById(id);

        return acaoUsuarioClienteOferta.orElseThrow(() -> new ObjectNotFoundException("AcaoUsuarioClienteOferta wih id ("+id+") not found"));
    }

    public List<AcaoUsuarioClienteOferta> findAll(){
        return repository.findAll();
    }

    public AcaoUsuarioClienteOferta save(AcaoUsuarioClienteOferta a){
        return repository.save(a);
    }

    public AcaoUsuarioClienteOferta update(AcaoUsuarioClienteOferta a) throws ObjectNotFoundException {
        Optional<AcaoUsuarioClienteOferta> acaoUsuarioClienteOfertaFromDB = repository.findById(a.getAcaoUsuarioClienteOfertaId());

        if(acaoUsuarioClienteOfertaFromDB.isPresent()){
            acaoUsuarioClienteOfertaFromDB.get().setAcaoUsuarioClienteOfertaDescricao(a.getAcaoUsuarioClienteOfertaDescricao());
            acaoUsuarioClienteOfertaFromDB.get().setAcao(a.getAcao());
            acaoUsuarioClienteOfertaFromDB.get().setAcaoUsuarioClienteOfertaData(a.getAcaoUsuarioClienteOfertaData());
            acaoUsuarioClienteOfertaFromDB.get().setClienteOferta(a.getClienteOferta());
            acaoUsuarioClienteOfertaFromDB.get().setUsuario(a.getUsuario());

            return acaoUsuarioClienteOfertaFromDB.get();
        }else{
            throw new ObjectNotFoundException("AcaoUsuarioClienteOferta Not Found");
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
