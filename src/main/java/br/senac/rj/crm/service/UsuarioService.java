package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Usuario;
import br.senac.rj.crm.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
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
        u.setUsuarioSenha(new BCryptPasswordEncoder().encode(u.getUsuarioSenha()));
        return repository.save(u);
    }

    public Usuario update(Usuario u) throws ObjectNotFoundException {
        Optional<Usuario> usuarioFromDB = repository.findById(u.getUsuarioId());

        if(usuarioFromDB.isPresent()){
            usuarioFromDB.get().setUsuarioNome(u.getUsuarioNome());
            if(!u.getUsuarioSenha().isEmpty() || u.getUsuarioSenha() == null){
                usuarioFromDB.get().setUsuarioSenha(new BCryptPasswordEncoder().encode(u.getUsuarioSenha()));
            }
            usuarioFromDB.get().setUsuarioCargo(u.getUsuarioCargo());
            usuarioFromDB.get().setUsuarioStatus(u.getUsuarioStatus());
            usuarioFromDB.get().setPerfilUsuario(u.getPerfilUsuario());

            return usuarioFromDB.get();
        }else{
            throw new ObjectNotFoundException("Usuario Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<Usuario> usuarioFromDB = repository.findById(id);
        if(usuarioFromDB.isPresent()){
            usuarioFromDB.get().setUsuarioStatus(false);
        }
    }

    public Usuario findByUsername(String username) throws ObjectNotFoundException {
        Optional<Usuario> usuario = repository.findByUsuarioLogin(username);

        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario wih username ("+username+") not found"));
    }

    public long getTotalInNumber() {
        return repository.count();
    }
}
