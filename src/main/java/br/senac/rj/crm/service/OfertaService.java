package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Oferta;
import br.senac.rj.crm.repository.OfertaRepository;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfertaService {
    
    @Autowired
    private OfertaRepository repository;

    public Oferta findById(Integer id) throws ObjectNotFoundException {
        Optional<Oferta> oferta = repository.findById(id);

        return oferta.orElseThrow(() -> new ObjectNotFoundException("Oferta wih id ("+id+") not found"));
    }

    public List<Oferta> findAll(){
        return repository.findAll();
    }

    public Oferta save(Oferta p){
        return repository.save(p);
    }

    public Oferta update(Oferta oferta) throws ObjectNotFoundException {
        Optional<Oferta> ofertaFromDB = repository.findById(oferta.getOfertaId());

        if(ofertaFromDB.isPresent()){
            ofertaFromDB.get().setOfertaStatus(oferta.getOfertaStatus());
            ofertaFromDB.get().setOfertaDataFim(oferta.getOfertaDataFim());
            ofertaFromDB.get().setOfertaDataInicio(oferta.getOfertaDataInicio());
            ofertaFromDB.get().setOfertaDescricao(oferta.getOfertaDescricao());
            ofertaFromDB.get().setOfertaPreco(oferta.getOfertaPreco());
            ofertaFromDB.get().setOfertaProduto(oferta.getOfertaProduto());

            return ofertaFromDB.get();
        }else{
            throw new ObjectNotFoundException("Oferta Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<Oferta> ofertaFromDB = repository.findById(id);
        if(ofertaFromDB.isPresent()){
            ofertaFromDB.get().setOfertaStatus(false);
        }
    }

}