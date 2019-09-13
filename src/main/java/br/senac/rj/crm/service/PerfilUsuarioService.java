package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.PerfilUsuario;
import br.senac.rj.crm.repository.PerfilUsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository repository;

    public PerfilUsuario findById(Integer id) throws ObjectNotFoundException {
        Optional<PerfilUsuario> perfil = repository.findById(id);

        return perfil.orElseThrow(() -> new ObjectNotFoundException("PerfilUsuario wih id ("+id+") not found"));
    }

    public List<PerfilUsuario> findAll(){
        return repository.findAll();
    }

    public PerfilUsuario save(@Valid PerfilUsuario perfil){
        return repository.save(perfil);
    }

    public PerfilUsuario update(PerfilUsuario perfil) throws ObjectNotFoundException {
        Optional<PerfilUsuario> perfilFromDB = repository.findById(perfil.getPerfilUsuarioId());

        if(perfilFromDB.isPresent()){
            perfilFromDB.get().setPerfilUsuarioDescricao(perfil.getPerfilUsuarioDescricao());
            perfilFromDB.get().setPerfilUsuarioStatus(perfil.getPerfilUsuarioStatus());

            return perfilFromDB.get();
        }else{
            throw new ObjectNotFoundException("PerfilUsuario Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<PerfilUsuario> usuarioFromDB = repository.findById(id);
        if(usuarioFromDB.isPresent()){
            usuarioFromDB.get().setPerfilUsuarioStatus(false);
        }
    }

}
