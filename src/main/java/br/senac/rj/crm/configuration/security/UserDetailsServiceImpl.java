package br.senac.rj.crm.configuration.security;

import br.senac.rj.crm.domain.Usuario;
import br.senac.rj.crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByUsuarioLogin(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("Dados invalidos ("+username+")");
        }
        return user.get();
    }
}
