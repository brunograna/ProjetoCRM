package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.CategoriaDado;
import br.senac.rj.crm.repository.CategoriaDadoRepository;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaDadoService {

    @Autowired
    private CategoriaDadoRepository repository;

    public CategoriaDado findById(Integer id) throws ObjectNotFoundException {
        Optional<CategoriaDado> categoriaDado = repository.findById(id);

        return categoriaDado.orElseThrow(() -> new ObjectNotFoundException("CategoriaDado wih id ("+id+") not found"));
    }

    public List<CategoriaDado> findAll(){
        return repository.findAll();
    }

    public CategoriaDado save(CategoriaDado c){
        return repository.save(c);
    }

    public CategoriaDado update(CategoriaDado c) throws ObjectNotFoundException {
        Optional<CategoriaDado> categoriaFromDB = repository.findById(c.getCategoriaDadoId());

        if(categoriaFromDB.isPresent()){
            categoriaFromDB.get().setCategoriaDadoDescricao(c.getCategoriaDadoDescricao());
            categoriaFromDB.get().setCategoriaDadoStatus(c.getCategoriaDadoStatus());

            return categoriaFromDB.get();
        }else{
            throw new ObjectNotFoundException("CategoriaDado Not Found");
        }
    }

    public void softDelete(CategoriaDado c) {
        Optional<CategoriaDado> categoriaFromDB = repository.findById(c.getCategoriaDadoId());
        if(categoriaFromDB.isPresent()){
            categoriaFromDB.get().setCategoriaDadoStatus(false);
        }
    }

}
