package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Usuario;
import br.senac.rj.crm.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) throws ObjectNotFoundException {
        Optional<Usuario> usuario = repository.findById(id);

        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario wih id ("+id+") not found"));
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario save(Usuario u){
        return repository.save(u);
    }

    public Usuario update(Usuario u) throws ObjectNotFoundException {
        Optional<Usuario> usuarioFromDB = repository.findById(u.getUsuarioId());

        if(usuarioFromDB.isPresent()){
            usuarioFromDB.get().setUsuarioNome(u.getUsuarioNome());
            usuarioFromDB.get().setUsuarioSenha(u.getUsuarioSenha());
            usuarioFromDB.get().setUsuarioCargo(u.getUsuarioCargo());
            usuarioFromDB.get().setUsuarioStatus(u.getUsuarioStatus());

            return usuarioFromDB.get();
        }else{
            throw new ObjectNotFoundException("Usuario Not Found");
        }
    }

    public void softDelete(Usuario u) {
        Optional<Usuario> usuarioFromDB = repository.findById(u.getUsuarioId());
        if(usuarioFromDB.isPresent()){
            usuarioFromDB.get().setUsuarioStatus(false);
        }
    }
}
