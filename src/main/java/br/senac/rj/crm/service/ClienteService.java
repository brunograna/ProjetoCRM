package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Cliente;
import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.repository.ClienteRepository;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) throws ObjectNotFoundException {
        Optional<Cliente> dadoTipo = repository.findById(id);

        return dadoTipo.orElseThrow(() -> new ObjectNotFoundException("Cliente wih id ("+id+") not found"));
    }

    public Cliente save(Cliente c){
        return repository.save(c);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente update(Cliente c) throws ObjectNotFoundException {
        Optional<Cliente> clienteFromDB = repository.findById(c.getClienteId());

        if(clienteFromDB.isPresent()){
            clienteFromDB.get().setClienteCpf(c.getClienteCpf());
            clienteFromDB.get().setClienteDado(c.getClienteDado());
            clienteFromDB.get().setClienteEmail(c.getClienteEmail());
            clienteFromDB.get().setClienteNome(c.getClienteNome());
            clienteFromDB.get().setClienteSobrenome(c.getClienteSobrenome());
            clienteFromDB.get().setClienteStatus(c.getClienteStatus());

            return clienteFromDB.get();
        }else{
            throw new ObjectNotFoundException("Cliente Not Found");
        }
    }

    public void softDelete(Cliente c) {
        Optional<Cliente> clienteFromDB = repository.findById(c.getClienteId());
        if(clienteFromDB.isPresent()){
            clienteFromDB.get().setClienteStatus(false);
        }
    }

}